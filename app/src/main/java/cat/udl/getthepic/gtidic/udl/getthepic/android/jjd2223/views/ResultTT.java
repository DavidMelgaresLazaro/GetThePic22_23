package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;



import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.GTimeTrial;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class ResultTT extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_tt);

        findViewById(R.id.MenuPrincipalResultTT).setOnClickListener(v -> MenuPrincipal());

        Intent intent = getIntent();
        int puntuacionTotal = intent.getIntExtra("Levels_TT",0);
        TextView textViewPuntuacion = findViewById(R.id.pointsTT);
        textViewPuntuacion.setText(String.valueOf(puntuacionTotal));

    }

    private void MenuPrincipal() {
        Intent intent = new Intent(ResultTT.this, menu.class);
        startActivity(intent);
        finish();
    }

}

