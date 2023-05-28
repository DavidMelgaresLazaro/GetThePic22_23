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

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerGame;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerMatch;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.AppCompatActivityPlus;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.GlobalInfo;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.providers.MultiplayerMatchesProvider;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Adapters.MultiplayerMatchesAdapter;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class MultiplayerGameSelector extends AppCompatActivityPlus {

    private MultiplayerMatchesProvider provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_game_selector);

//        getFirstGameAvailable();
        initRecylerView();
    }

    private void initRecylerView() {
        provider = new MultiplayerMatchesProvider();

        MultiplayerMatchesAdapter adapter =  new MultiplayerMatchesAdapter(provider.getLaMevaLlista(), multiplayerMatch -> jumpToGamev2(multiplayerMatch));
        provider.setAdapter(adapter);
        provider.getFromFirebase();

        RecyclerView rv = findViewById(R.id.rv_matches);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);
    }

    private void jumpToGamev2(MultiplayerMatch multiplayerMatch) {
        String firebaseKey = multiplayerMatch.getMatchKey();
        Intent i = new Intent(this.getApplicationContext(), Multiplayer.class);
        i.putExtra(MultiplayerGame.MULTIPLAYER_KEY, MultiplayerGame.MULTIPLAYER_TYPE_CONNECT);
        i.putExtra(MultiplayerGame.MULTIPLAYER_GAME_KEY, firebaseKey);

        finish();
        provider.detach();

        startActivity(i);
    }
}