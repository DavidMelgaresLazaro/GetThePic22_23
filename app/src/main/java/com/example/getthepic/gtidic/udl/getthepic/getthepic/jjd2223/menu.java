package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity {
    Button about;
    Button jugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        about = findViewById(R.id.button2);
        jugar = findViewById(R.id.buttonstart);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( menu.this , About.class);
                startActivity(intent);

            }
        });
        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( menu.this , GameActivity.class);
                startActivity(intent);

            }
        });
    }
}