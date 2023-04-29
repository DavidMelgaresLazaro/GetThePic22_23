package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.GlobalInfo;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.entrada);
        // do heavy load things here
        // example case wait for N seconds and end this activity
        new Handler().postDelayed(() -> endActivity(), GlobalInfo.getInstance().getSPLASH_SCREEN_TIMEOUT());
    }

    private void endActivity(){
        Intent i = new Intent(this, menu.class);
        startActivity(i);
        finish();
    }
}