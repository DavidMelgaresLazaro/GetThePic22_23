package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.LongToIntFunction;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.ActivityHelper;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class LoginActivity extends AppCompatActivity {

    protected String myClassTag = this.getClass().getSimpleName();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText etEmail;
    EditText etPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etEmail = findViewById(R.id.etEmailLogin);
        etPassword = findViewById(R.id.etPasswordLogin);
        findViewById(R.id.btLogin).setOnClickListener(v -> login());
        findViewById(R.id.btLoginToSignUp).setOnClickListener(v -> loginToSignUp());
        findViewById(R.id.btFWPass).setOnClickListener((v ->  loginToFWPass()));
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }


    private void loginToFWPass() {
        Intent i = new Intent(this, ForgotPasswordActivity.class);
        startActivity(i);
    }
    private void loginToSignUp() {
        Intent i = new Intent(this, SignInActivity.class);
        startActivity(i);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        reload();
    }


    private void login() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        ActivityHelper.hideKeyboard(this);

        if (email.trim().isEmpty() || password.trim().isEmpty()){
            Toast.makeText(this, "loginNoValidEmailPassword", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(myClassTag, "signInWithEmail:success");
                        reload();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(myClassTag, "signInWithEmail:failure", task.getException());
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        etPassword.setText("");
                    }
                });
    }

    private void reload(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            if (currentUser.isEmailVerified()){
                crearUsuarioFirestore(currentUser);
                etPassword.setText("");
                etEmail.setText("");
                Intent i = new Intent(this, SplashScreen.class);
                startActivity(i);
            }else{
                Toast.makeText(this, R.string.VerifyEmail,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void crearUsuarioFirestore(FirebaseUser user) {
        DocumentReference userRef = db.collection("usuarios").document(user.getUid());

        userRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (!document.exists()) {
                    // No existe un documento de usuario para este usuario, crear uno nuevo
                    Map<String, Object> newUser = new HashMap<>();
                    newUser.put("nombre", user.getDisplayName());
                    newUser.put("correo", user.getEmail());
                    newUser.put("last_login", new Date());
                    newUser.put("points", 0);
                    newUser.put("last_level", 0);
                    GlobalInfo.getInstance().setLast_login(document.getDate("last_login"));

                    userRef.set(newUser)
                            .addOnSuccessListener(aVoid -> Log.d(TAG, "Documento de usuario creado correctamente"))
                            .addOnFailureListener(e -> Log.e(TAG, "Error al crear el documento de usuario", e));
                } else {
                    GlobalInfo.getInstance().setLast_login(document.getDate("last_login"));
                    userRef.update("last_login",new Date());
                    Log.d(TAG, "Documento de usuario ya existe para este usuario");
                }
            } else {
                Log.e(TAG, "Error al obtener el documento de usuario", task.getException());
            }
        });
    }
}