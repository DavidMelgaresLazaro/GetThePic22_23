package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Models.Game;
public class GameActivity extends AppCompatActivity{

    private Game internalGame = new Game();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaming);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String nomJugador1 = extra.getString("nomDelJugador1");
            if (nomJugador1 != null) {
                Toast.makeText(this, nomJugador1, Toast.LENGTH_LONG).show();
            }
        }
        //showPopup(); TODO

    }

    //MENU PAUSA GAME:  TODO
    /*
    public void showPopup() {
        // Anchor popoup with layout to "center" menu
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.);
        PopupMenu popup = new PopupMenu(this, layout);
        popup.setOnMenuItemClickListener(this);
        popup.getMenuInflater().inflate(R.layout.pause, popup.getMenu());
        popup.show();
    }

    @Override
    public void onMenuItemClick(MenuItem item) {
        dosomething();
        resume();

    }
    */


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

