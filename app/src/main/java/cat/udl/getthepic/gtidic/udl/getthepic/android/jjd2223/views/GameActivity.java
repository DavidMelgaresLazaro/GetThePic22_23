package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;


import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels.GameViewModel;


import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.databinding.GamingBinding;

public class GameActivity extends AppCompatActivity {

    /**
     * La variable game representa el ViewModel
     */
    GameViewModel game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaming);


        /***
         * Inicialitzem el DataBinding
         */
        game = new ViewModelProvider(this).get(GameViewModel.class);
        GamingBinding binding = DataBindingUtil.setContentView(this, R.layout.gaming);
        binding.setGameViewModel(game);
        binding.setLifecycleOwner(this);


        findViewById(R.id.finishbutton).setOnClickListener(view -> finishgameactivity());
        findViewById(R.id.restart).setOnClickListener(view -> restart());

        game.setContext(getApplicationContext());
        game.saveGameIntoDB();

        game.getGame().observe(this, game -> {
            if(game.jocacabat == true)
            {
                jocacabat();
            }
        });
    }

    /***
     * Al pitjar restart, començem l'activitat de nou
     */
    private void restart() {
        Intent intent = new Intent(GameActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    /***
     * Al pitjar finish, ens durà a la activitat de menu i sortira de jugar
     */
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

    private void jocacabat()
    {
        Intent intent = new Intent(this,FinishActivity.class);
        startActivity(intent);
        finish();
    }

}


