package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;



import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.ActivityHelper;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.AppCompatActivityPlus;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class SignInActivity extends AppCompatActivityPlus {

    private FirebaseAuth mAuth;

    EditText etEmail;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        etEmail = findViewById(R.id.etEmailSignIn);
        etPassword = findViewById(R.id.etPasswordSignIn);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.btSignUp).setOnClickListener(v -> signUp());
    }

    private void signUp() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        ActivityHelper.hideKeyboard(this);

        if (email.trim().isEmpty() || password.trim().isEmpty()){
            Toast.makeText(this, "Email o contrasenya incorrectes", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(myClassTag, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification();
                            mAuth.signOut();
                            Toast.makeText(SignInActivity.this, R.string.VerifyEmail,
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(myClassTag, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "He tingut un problema enviant-te l'email, ho sento tio.",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }
}