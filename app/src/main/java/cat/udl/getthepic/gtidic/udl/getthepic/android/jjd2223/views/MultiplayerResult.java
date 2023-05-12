package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class MultiplayerResult extends AppCompatActivity {

    private int Points,PointsOponent;
    private String selfName, nomOponent;
    private boolean oponent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_result);

        Points = getIntent().getIntExtra("points",0);
        PointsOponent = getIntent().getIntExtra("pointsOponent",0);
        selfName = getIntent().getStringExtra("selfName");
        nomOponent = getIntent().getStringExtra("nomOponent");
        oponent = getIntent().getBooleanExtra("oponent",false);


        if(Points == PointsOponent)
        {
            //Empate
        }
        if(oponent)
        {
            if(PointsOponent >Points)
            {

            }
        }



    }
}