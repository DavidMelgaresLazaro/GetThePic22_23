package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.DB.DatabaseGetThePic;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.IAPlayer;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class Others extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);

        findViewById(R.id.laiajuga).setOnClickListener(view -> jugalaia());
        findViewById(R.id.borrarDB).setOnClickListener(view -> clearDB());
        findViewById(R.id.veureDB).setOnClickListener(view -> getPoints());
        findViewById(R.id.menureturn).setOnClickListener(view -> menu());

    }

    private void jugalaia()
    {
        IAPlayer iaPlayer = new IAPlayer();
        iaPlayer.init(this);
        iaPlayer.jugar();
    }
    private void clearDB() {
        String dbName = "GTP.db";
        DatabaseGetThePic dbRoom = Room.databaseBuilder(this.getApplicationContext(), DatabaseGetThePic.class, dbName).allowMainThreadQueries().build();
        dbRoom.gameDAO().deleteAll();
        dbRoom.close();
    }

    private void getPoints() {
        String dbName = "GTP.db";
        DatabaseGetThePic dbRoom = Room.databaseBuilder(this.getApplicationContext(), DatabaseGetThePic.class, dbName).allowMainThreadQueries().build();
        int lastPoints = dbRoom.gameDAO().getLastGamePoints();
        int maxPoints = dbRoom.gameDAO().getMaxPoints();
        String missatge = String.format("Max punts: %d. Ultims punts: %d", maxPoints, lastPoints );
        System.out.println(missatge);
        dbRoom.close();
    }
    private void menu()
    {
        Intent intent = new Intent(this, menu.class);
        finish();
    }
}