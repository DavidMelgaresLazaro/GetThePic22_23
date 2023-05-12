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
    private TextView resultat = findViewById(R.id.resultat);
    private Button menu = findViewById(R.id.menubuttonretornar);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_result);

        menu.setOnClickListener(v -> retornarmenu());


        Points = getIntent().getIntExtra("points",0);
        PointsOponent = getIntent().getIntExtra("pointsOponent",0);
        selfName = getIntent().getStringExtra("selfName");
        nomOponent = getIntent().getStringExtra("nomOponent");
        oponent = getIntent().getBooleanExtra("oponent",false);


        if(Points == PointsOponent)
        {
            //Empate
            resultat.setText("Heu empatat!");
        }
        if(oponent)
        {
            if(PointsOponent >Points)
            {
                resultat.setText("Has guanyat!");
            }else {
                resultat.setText("Has perdut!");
            }
        }else {
            if(Points > PointsOponent)
            {
                resultat.setText("Has guanyat!");
            }else {
                resultat.setText("Has perdut!");
            }
        }



    }
    private void retornarmenu()
    {
        Intent i = new Intent(this, menu.class);
        startActivity(i);
        finish();
    }
}