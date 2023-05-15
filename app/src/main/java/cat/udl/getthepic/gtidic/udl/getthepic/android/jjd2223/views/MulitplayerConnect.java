package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class MulitplayerConnect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulitplayer_connect);

        findViewById(R.id.connect).setOnClickListener(view -> playMultiplayerConnect());
        findViewById(R.id.create).setOnClickListener(view -> playMultiplayerCreate());
    }

    private void playMultiplayerCreate(){
        Intent i = new Intent(this, Multiplayer.class);
        i.putExtra("TIPO",true);
        startActivity(i);
        finish();
    }

    private void playMultiplayerConnect(){
        Intent i = new Intent(this, MultiplayerGameSelector.class);
        startActivity(i);
        finish();
    }
}