package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerGame;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels.MultiplayerViewModel;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.databinding.MultiplayerBinding;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.generated.callback.OnClickListener;

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



            if (extra != null){
                boolean multiplayerType = extra.getBoolean("TIPO");
                if (multiplayerType == true) multiplayer.multiplayerCreate();
                if (multiplayerType == false) multiplayer.multiplayerConnect(extra.getString(MultiplayerGame.MULTIPLAYER_GAME_KEY));
            }
            findViewById(R.id.finishbutton).setOnClickListener(view -> finishgameactivity());



            multiplayer.getTime().observe(this, Time -> {
                if(Time == 0)
                {
                    jocAcabat();

                }
            });


    }


    private void jocAcabat()
    {
        Intent intent =  new Intent(this,MultiplayerResult.class);
        intent.putExtra("points",multiplayer.getMultiplayerGame().getValue().maxPoints);
        intent.putExtra("pointsOponent",multiplayer.getMultiplayerGame().getValue().maxPointsOponent);
        intent.putExtra("nomOponent",multiplayer.getMultiplayerGame().getValue().oponentName);
        intent.putExtra("oponent",multiplayer.oponent);
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