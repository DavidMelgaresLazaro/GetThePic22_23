package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class MultiplayerResult extends AppCompatActivity {

    private int Points,PointsOponent;
    private String selfName, nomOponent;
    private boolean oponent;
    private TextView resultat,Jugador,Oponent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_result);


        findViewById(R.id.menubuttonretornar).setOnClickListener(v -> retornarmenu());
        resultat = findViewById(R.id.resultat);
        Jugador = findViewById(R.id.Jugador);
        Oponent = findViewById(R.id.Oponent);


        Points = getIntent().getIntExtra("points",0);
        PointsOponent = getIntent().getIntExtra("pointsOponent",0);
        nomOponent = getIntent().getStringExtra("nomOponent");
        oponent = getIntent().getBooleanExtra("oponent",false);
        System.out.println("soc" + oponent);


        Jugador.setText("Has fet: " + Points + " punts");
        Oponent.setText("El jugador " + nomOponent + " ha fet " + PointsOponent + " Punts");


        if(Points == PointsOponent)
        {
            //Empate
            resultat.setText("Heu empatat!");
        }else {
        if(oponent)
        {
            System.out.println("El jugador " + nomOponent + " ha fet " + PointsOponent + " Punts");
            if(PointsOponent > Points)
            {
                resultat.setText("Has guanyat!");
            }else {
                resultat.setText("Has perdut!");
            }
        }else{
            System.out.println("Has fet: " + Points + " punts");
            if(Points > PointsOponent)
            {
                resultat.setText("Has guanyat!");
            }else {
                resultat.setText("Has perdut!");
            }
        }}



    }
    private void retornarmenu()
    {
        Intent i = new Intent(this, menu.class);
        startActivity(i);
        finish();
    }
}