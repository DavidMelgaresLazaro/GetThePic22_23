package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    /**
     * Cream les nostres variables:
     * userEmail: l'usuari introdueix el seu mail per poder recuperar la contrasenya
     * send: És el botó que l'usuari pitjarà per a enviar el correu
     * firebaseAuth: variable on guardarem la instància de Firebase per a utilitzar els serveis de Firebase
     */
    EditText userEmail;
    Button send;
    FirebaseAuth firebaseAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        userEmail = findViewById(R.id.etEmailFWPass);
        send = findViewById(R.id.btFWSubmit);
        //Recuparem la instància
        firebaseAuth = FirebaseAuth.getInstance();

        send.setOnClickListener(view -> sendPasswordRecover());


    }

    private void sendPasswordRecover()
    {
        /**
         *Desde la instància firebaseAuth, utilitzarem la seva funció sendPasswordResetEmail per a tal de enviar
         * un correu de recuperació al mail que ha introduit l'usuari al TextView userEmail. En cas que "task
         * sigui satisfactori fara un Toast del resultat.
         */
        firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, R.string.ToastSendFW,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ForgotPasswordActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
