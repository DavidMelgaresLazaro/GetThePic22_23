package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.GTimeTrial;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class ResultTT extends AppCompatActivity {
    GTimeTrial gTimeTrial = new GTimeTrial();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_tt);

        String last_level = getIntent().getStringExtra("last_level");
        System.out.println(last_level);


        findViewById(R.id.MenuPrincipalResultTT).setOnClickListener(v -> MenuPrincipal());
        TextView maxLevels = findViewById(R.id.pointsTT);
        maxLevels.setText(last_level);


    }


    private void MenuPrincipal() {
        Intent intent = new Intent(ResultTT.this, menu.class);
        startActivity(intent);
        finish();
    }
}

