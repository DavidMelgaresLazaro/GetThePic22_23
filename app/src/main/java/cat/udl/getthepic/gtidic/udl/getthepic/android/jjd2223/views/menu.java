package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class menu extends AppCompatActivity {
    Button about,start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        about = findViewById(R.id.button2);
        start = findViewById(R.id.buttonstart);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( menu.this , About.class);
                startActivity(intent);

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, GameActivity.class);
                startActivity(intent);

            }
        });
    }
}