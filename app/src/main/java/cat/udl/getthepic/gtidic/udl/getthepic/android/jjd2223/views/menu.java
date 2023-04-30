package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;


import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.DB.DatabaseGetThePic;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.IAPlayer;

public class menu extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore FBdb = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button2).setOnClickListener(view -> aboutpage());
        findViewById(R.id.buttonstart).setOnClickListener(view -> StartGame());
        findViewById(R.id.userinfo).setOnClickListener(view -> userinfo());
        findViewById(R.id.others).setOnClickListener(v -> others());
        findViewById(R.id.logout).setOnClickListener(v -> logout());
        findViewById(R.id.Contrarellotge).setOnClickListener(view -> TimeTrial());


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        printuser(user);

    }

    /***
     * A la instància de Firebase fa un log out de l'usuari i acaba la activitat de menu per a tal que vagi al login
     */
    private void logout() {
        mAuth.signOut();
        finish();
    }

    private void TimeTrial(){
        Intent intent= new Intent(menu.this, TimeTrial.class);
        startActivity(intent);
    }


    private void aboutpage()
    {
        Intent intent = new Intent( menu.this , About.class);
        startActivity(intent);
    }

    private void userinfo()
    {
        Intent intent = new Intent(menu.this, UserInfo.class);
        startActivity(intent);
    }

    private void StartGame()
    {
        Intent intent = new Intent(menu.this, GameActivity.class);
        startActivity(intent);

    }
    private void others()
    {
        Intent intent = new Intent(menu.this,Others.class);
        startActivity(intent);
    }

    /***
     * De la instància de l'¡usuari actual, imprimim per Toast els paràmetres que ens interesen
     * @param user
     */
    private void printuser(FirebaseUser user)
    {
        if(user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            Toast.makeText(this, "Hola Usuari " + email, Toast.LENGTH_SHORT).show();
        }
    }
}