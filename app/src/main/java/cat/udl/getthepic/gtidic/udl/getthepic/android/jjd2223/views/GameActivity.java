package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;


import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.levels;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels.GameViewModel;


import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;
import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.databinding.GamingBinding;

public class GameActivity extends AppCompatActivity {

    GameViewModel game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaming);



        game = new ViewModelProvider(this).get(GameViewModel.class);
        GamingBinding binding = DataBindingUtil.setContentView(this, R.layout.gaming);
        binding.setGameViewModel(game);
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
        game.getGame().observe(this, game -> {
            if(game.win == true)
            {
                Toast.makeText(this,"Molt bé,! Seguent Nivell", Toast.LENGTH_SHORT).show();
            }
            if(game.equivocat == true)
            {
                Toast.makeText(this, "T'has equivocat, torna a intentar-ho", Toast.LENGTH_SHORT).show();
            }
        });
        game.setContext(getApplicationContext());
        game.saveGameIntoDB();
    }

    private void restart() {
        Intent intent = new Intent(GameActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }


}


