package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;
import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.databinding.GamingBinding;
import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.viewmodels.GameActivityViewModel;

public class GameActivity extends AppCompatActivity {

    GameActivityViewModel game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaming);


        game = new ViewModelProvider(this).get(GameActivityViewModel.class);
        GamingBinding binding = DataBindingUtil.setContentView(this, R.layout.gaming);
        binding.setGameActivityViewModel(game);
        binding.setLifecycleOwner(this);

        findViewById(R.id.finishbutton).setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            // Finish the current activity
            finish();
        });
        findViewById(R.id.restart).setOnClickListener(view -> {
            restart();
        });

        /*showCards();

        game.getGame().observe(this, game -> {
            if(game.win == true)
            {

                showCards();
                Toast.makeText(this, "Seguent Nivell!", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "+100xp", Toast.LENGTH_SHORT).show();
            }
            if(game.equivocat == true)
            {
                showCards();
            }
        });*/


    }

    private void showCards(){
        for(int i = 0; i < 8; i ++ )
        {
            game.getGame().getValue().board.getPiece(i).girada = true;
        }
        new Handler().postDelayed(() -> hideCards(),4000);

    }



    private void hideCards() {
        for(int i = 0; i < 8; i ++ )
        {
            game.getGame().getValue().board.getPiece(i).girada = false;
        }

    }

    private void restart() {
        Intent intent = new Intent(GameActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }


}
