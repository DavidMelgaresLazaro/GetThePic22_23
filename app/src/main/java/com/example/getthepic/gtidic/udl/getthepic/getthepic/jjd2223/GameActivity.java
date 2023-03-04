package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.databinding.GamingBinding;

public class GameActivity extends AppCompatActivity {

    private int level = GlobalInfo.getInstance().getLastLevel();

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
            Intent intent = new Intent( GameActivity.this , GameActivity.class);
            startActivity(intent);
            finish();
        });
        createListenersForButtons();
        showCards();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideCards();
            }
        }, 4000);
    }

    private void showCards(){
        findViewById(R.id.button0).setVisibility(View.VISIBLE);
        findViewById(R.id.button1).setVisibility(View.VISIBLE);
        findViewById(R.id.button2).setVisibility(View.VISIBLE);
        findViewById(R.id.button3).setVisibility(View.VISIBLE);
        findViewById(R.id.button4).setVisibility(View.VISIBLE);
        findViewById(R.id.button5).setVisibility(View.VISIBLE);
        findViewById(R.id.button6).setVisibility(View.VISIBLE);
        findViewById(R.id.button7).setVisibility(View.VISIBLE);
    }
    private void hideCards(){
        findViewById(R.id.button0).setVisibility(View.GONE);
        findViewById(R.id.button1).setVisibility(View.GONE);
        findViewById(R.id.button2).setVisibility(View.GONE);
        findViewById(R.id.button3).setVisibility(View.GONE);
        findViewById(R.id.button4).setVisibility(View.GONE);
        findViewById(R.id.button5).setVisibility(View.GONE);
        findViewById(R.id.button6).setVisibility(View.GONE);
        findViewById(R.id.button7).setVisibility(View.GONE);
        //Thread.sleep(5000);
    }



    private void createListenersForButtons() {
//      TODO it have to be improved in the future! -> creating buttons dynamically ;)
        findViewById(R.id.button0).setOnClickListener(view -> buttonClicked(view, 0));
        findViewById(R.id.button1).setOnClickListener(view -> buttonClicked(view, 1));
        findViewById(R.id.button2).setOnClickListener(view -> buttonClicked(view, 2));
        findViewById(R.id.button3).setOnClickListener(view -> buttonClicked(view, 3));

        findViewById(R.id.button4).setOnClickListener(view -> buttonClicked(view, 4));
        findViewById(R.id.button5).setOnClickListener(view -> buttonClicked(view, 5));
        findViewById(R.id.button6).setOnClickListener(view -> buttonClicked(view, 6));
        findViewById(R.id.button7).setOnClickListener(view -> buttonClicked(view, 7));
    }


    private void buttonClicked(View view, int row) {
        game.cardClicked(view, row);
    }
}


















    /*
        gameActivityViewModel.getGame().observe(this, new Observer<game>() {
            @Override
            public void onChanged(List<Piece> game) {

            }
        }{*/


        /*findViewById(R.id.finishbutton).setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            // Finish the current activity
            finish();
        });
        while(level != 0)
        {
            internalGame.board.load(level);
            level++;
            System.out.println("arribat");
        }*/



    //VISIBILITAT TODO
    //findViewById(R.id.button11).setVisibility();
    /*
    private void RestartGame() {
        try {
            endActivity();
        } catch (Exception e) {
            Log.d("ERROR", e.toString());
        }
    }

    private void endActivity(){
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
        finish();
    }

    /*private void buttonClicked(View view, int row){
        internalGame.cardClicked((Button) view, row);
    }


    //TODO
    private void showCards(){
        //.SetVisibility.(View.VISIBLE)
    }
    private void hideCards(){
        //.SetVisibility.(View.GONE)*/

