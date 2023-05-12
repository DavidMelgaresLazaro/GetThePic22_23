package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;



import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.GTimeTrial;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels.GTTViewModel;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class ResultTT extends AppCompatActivity {
    GTimeTrial gTimeTrial = new GTimeTrial();
    private int puntuacionTotal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_tt);


        findViewById(R.id.MenuPrincipalResultTT).setOnClickListener(v -> MenuPrincipal());




        ResultadoTT();
        Intent intent = getIntent();
        puntuacionTotal = intent.getIntExtra("puntuacioTotal", 0);
        TextView textViewPuntuacion = findViewById(R.id.pointsTT);
        textViewPuntuacion.setText(String.valueOf(puntuacionTotal));

    }


    public void ResultadoTT() {


        // Crea un intent para iniciar la segunda actividad (ResultTT)
        Intent intent = new Intent(this, ResultTT.class);

        // Obtiene la puntuación total llamando al método getLevelsTotal()
        int puntuacion = gTimeTrial.getLevelsTotal();

        // Agrega la puntuación como un extra al intent con la clave "puntuacionTotal"
        intent.putExtra("puntuacioTotal", puntuacion);

        // Inicia la segunda actividad utilizando el contexto proporcionado
        startActivity(intent);
        finish();

    }


    private void MenuPrincipal() {
        Intent intent = new Intent(ResultTT.this, menu.class);
        startActivity(intent);
        finish();
    }




}

