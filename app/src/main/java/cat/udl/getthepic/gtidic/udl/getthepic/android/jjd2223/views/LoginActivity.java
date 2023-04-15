package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.ActivityHelper;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class LoginActivity extends AppCompatActivity {

    protected String myClassTag = this.getClass().getSimpleName();

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

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(myClassTag, "signInWithEmail:success");
                            reload();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(myClassTag, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            etPassword.setText("");
                        }
                    }
                });
    }

    private void reload(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            if (currentUser.isEmailVerified()){
                etPassword.setText("");
                etEmail.setText("");
                Intent i = new Intent(this, menu.class);
                startActivity(i);
            }else{
                Toast.makeText(LoginActivity.this, R.string.VerifyEmail,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}