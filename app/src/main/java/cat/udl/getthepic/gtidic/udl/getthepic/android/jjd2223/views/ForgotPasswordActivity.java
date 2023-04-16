package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class ForgotPasswordActivity extends AppCompatActivity {


    EditText userEmail;
    Button userPass;

    FirebaseAuth firebaseAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        userEmail = findViewById(R.id.etEmailFWPass);
        userPass = findViewById(R.id.btFWSubmit);


        firebaseAuth = FirebaseAuth.getInstance();

        userPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPasswordActivity.this, "Password send to your email",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(ForgotPasswordActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                    }
                });
            }
        });


    }

}
