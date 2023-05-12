package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import java.util.Locale;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.GTimeTrial;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels.GTTViewModel;


import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.databinding.ActivityTimeTrialBinding;

public class TimeTrial extends AppCompatActivity {

    /**
     * La variable game representa el ViewModel
     */
    GTTViewModel game;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_trial);


        /***
         * Inicialitzem el DataBinding
         */
        game = new ViewModelProvider(this).get(GTTViewModel.class);
        ActivityTimeTrialBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_time_trial);
        binding.setGTTViewModel(game);
        binding.setLifecycleOwner(this);


        findViewById(R.id.finishbutton).setOnClickListener(view -> finishgameactivity());
        findViewById(R.id.restart).setOnClickListener(view -> restart());

        game.setContext(getApplicationContext());


        game.getTime().observe(this, Time -> {
            if(Time == 0)
            {
                finishgameactivity();

            }
        });




    }







    /***
     * Al pitjar restart, començem l'activitat de nou
     */
    private void restart() {

        Intent intent = new Intent(TimeTrial.this, TimeTrial.class);
        startActivity(intent);

        finish();
    }

    /***
     * Al pitjar finish, ens durà a la activitat de menu i sortira de jugar
     */
    private void finishgameactivity()
    {


        // Define un objeto Intent para la actividad de destino
        Intent intent = new Intent(this, ResultTT.class);
        System.out.println(String.valueOf(game.getGame().getValue().getLevelsTotal()));
        intent.putExtra("Levels_TT", game.getGame().getValue().getLevelsTotal());

        // Inicia la actividad de destino
        startActivity(intent);


        // Finaliza la actividad actual
        finish();

    }

}


