package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerGame;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels.MultiplayerViewModel;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.databinding.MultiplayerBinding;

public class Multiplayer extends AppCompatActivity {

    private MultiplayerViewModel multiplayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer);

        Bundle extra = getIntent().getExtras();



        multiplayer = new ViewModelProvider(this).get(MultiplayerViewModel.class);
        MultiplayerBinding binding = DataBindingUtil.setContentView(this, R.layout.multiplayer);
        binding.setMultiplayerViewModel(multiplayer);
        binding.setLifecycleOwner(this);


        multiplayer.getTime().observe(this, Time -> {
            if(Time == 0)
            {
                int selfPoints = multiplayer.getMultiplayerGame().getValue().maxPoints;
                int oponentPoints = multiplayer.getMultiplayerGame().getValue().maxPointsOponent;
                String selfName = multiplayer.selfName;
                String oponentName = multiplayer.getMultiplayerGame().getValue().oponentName;
                boolean oponent = multiplayer.oponent;


                jocAcabat(selfPoints,oponentPoints,selfName,oponentName,oponent);

            }
        });

        if (extra != null){
            boolean multiplayerType = extra.getBoolean("TIPO");
            if (multiplayerType == true) multiplayer.multiplayerCreate();
            if (multiplayerType == false) multiplayer.multiplayerConnect(extra.getString(MultiplayerGame.MULTIPLAYER_GAME_KEY));
        }
        findViewById(R.id.finishbutton).setOnClickListener(view -> finishgameactivity());


    }

    private void jocAcabat(int Points,int PointsOponent,String selfName,String nomOponent,Boolean oponent)
    {
        Intent intent =  new Intent(this,MultiplayerResult.class);
        intent.putExtra("points",Points);
        intent.putExtra("pointsOponent",PointsOponent);
        intent.putExtra("nomOponent",nomOponent);
        intent.putExtra("selfName",selfName);
        intent.putExtra("oponent",oponent);
        startActivity(intent);
        finish();
    }

    private void finishgameactivity()
    {
        // Define a return Key Value
        Intent intent = new Intent();

        // Put the data to return into the extra

        // Set the activity's result to RESULT_OK
        setResult(RESULT_OK, intent);

        // Finish the current activity
        finish();
    }
}