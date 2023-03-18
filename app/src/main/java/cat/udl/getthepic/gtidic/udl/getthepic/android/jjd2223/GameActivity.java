package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.levels;

import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;
import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.databinding.GamingBinding;

public class GameActivity extends AppCompatActivity {

    GameActivityViewModel game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaming);


        game = new ViewModelProvider(this).get(GameActivityViewModel.class);
        GamingBinding binding = DataBindingUtil.setContentView(this, R.layout.gaming);
        binding.setGameActivityViewModel(game);
        binding.setLifecycleOwner(this);

        findViewById(R.id.finishbutton).setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            // Finish the current activity
            finish();
        });
        findViewById(R.id.restart).setOnClickListener(view -> {
            restart();
        });

        nextlevelimage();
        showCards();

        game.getGame().observe(this, game -> {
            if(game.win == true)
            {
                showCards();
                Toast.makeText(this, "Seguent Nivell!", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "+100xp", Toast.LENGTH_SHORT).show();
            }
            if(game.viewbutton != null)
            {
                showCard(game.viewbutton);
            }
            nextlevelimage();
        });

    }

    private void showCard(Button button)
    {
        button.setTextSize(14);
    }
    private void nextlevelimage()
    {
        int imageResource = getResources().getIdentifier(levels.Getimage(GlobalInfo.getInstance().getLastLevel()), null, getPackageName());
        ((ImageView)findViewById(R.id.fotojoc)).setImageResource(imageResource);
    }

    private void showCards() {
        ((Button) findViewById(R.id.button0)).setTextSize(14);
        ((Button) findViewById(R.id.button1)).setTextSize(14);
        ((Button) findViewById(R.id.button2)).setTextSize(14);
        ((Button) findViewById(R.id.button3)).setTextSize(14);
        ((Button) findViewById(R.id.button4)).setTextSize(14);
        ((Button) findViewById(R.id.button5)).setTextSize(14);
        ((Button) findViewById(R.id.button6)).setTextSize(14);
        ((Button) findViewById(R.id.button7)).setTextSize(14);
        ((Button) findViewById(R.id.button7)).setTextSize(14);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideCards();
            }
        }, 4000);
    }

    private void hideCards() {
        ((Button) findViewById(R.id.button0)).setTextSize(0);
        ((Button) findViewById(R.id.button1)).setTextSize(0);
        ((Button) findViewById(R.id.button2)).setTextSize(0);
        ((Button) findViewById(R.id.button3)).setTextSize(0);
        ((Button) findViewById(R.id.button4)).setTextSize(0);
        ((Button) findViewById(R.id.button5)).setTextSize(0);
        ((Button) findViewById(R.id.button6)).setTextSize(0);
        ((Button) findViewById(R.id.button7)).setTextSize(0);
    }

    private void restart() {
        Intent intent = new Intent(GameActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }


}


















    /*
        gameActivityViewModel.getGame().observe(this, new Observer<game>() {
            @Override
            public void onChanged(List<Piece> game) {

            }
        }{*/


        /*findViewById(R.id.finishbutton).setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            // Finish the current activity
            finish();
        });
        while(level != 0)
        {
            internalGame.board.load(level);
            level++;
            System.out.println("arribat");
        }*/

/*
    private void endActivity(){
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
        finish();
    }

    /*private void buttonClicked(View view, int row){
        internalGame.cardClicked((Button) view, row);
    }


    //TODO
    private void showCards(){
        //.SetVisibility.(View.VISIBLE)
    }
    private void hideCards(){
        //.SetVisibility.(View.GONE)*/

