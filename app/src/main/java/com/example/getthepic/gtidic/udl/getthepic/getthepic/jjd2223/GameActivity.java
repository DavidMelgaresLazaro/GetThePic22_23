package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import Models.Game;

public class GameActivity extends AppCompatActivity{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game game = new Game();

    }

    //MENU PAUSA GAME:  TODO
    /*
    public void showPopup() {
        // Anchor popoup with layout to "center" menu
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.menuLayout);
        PopupMenu popup = new PopupMenu(this, layout);
        popup.setOnMenuItemClickListener(this);
        popup.getMenuInflater().inflate(R.layout.pause, popup.getMenu());
        popup.show();
    }

    @Override
    public void onMenuItemClick(MenuItem item) {
        dosomething();
        resume();

    }*/


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
}

