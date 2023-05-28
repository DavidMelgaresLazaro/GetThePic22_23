package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerGame;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.AppCompatActivityPlus;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.providers.MultiplayerMatchesProvider;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.GlobalInfo;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Adapters.MultiplayerMatchesAdapter;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class MultiplayerGameSelector extends AppCompatActivityPlus {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_game_selector);

        //getFirstGameAvailable();
        initRecylerView();
    }

    private void initRecylerView() {
        MultiplayerMatchesProvider provider = new MultiplayerMatchesProvider();
        MultiplayerMatchesAdapter adapter =  new MultiplayerMatchesAdapter(provider.getLaMevaLlista());
        provider.setAdapter(adapter);
        provider.getFromFirebase();

        RecyclerView rv = findViewById(R.id.rv_matches);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
//        pending things to do
    }

    /**
     * Connecta (de moment) amb el primer game creat que no hagi estat matchat
     */


    private void jumpToGame(String firebaseKey){
        Intent i = new Intent(this, Multiplayer.class);
        i.putExtra(MultiplayerGame.MULTIPLAYER_KEY, MultiplayerGame.MULTIPLAYER_TYPE_CONNECT);
        i.putExtra(MultiplayerGame.MULTIPLAYER_GAME_KEY, firebaseKey);
        startActivity(i);
    }
}