package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.providers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Adapters.MultiplayerMatchesAdapter;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.GlobalInfo;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerGame;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerMatch;

public class MultiplayerMatchesProvider {

    MultiplayerMatchesAdapter adapter;
    private DatabaseReference myFirebaseDBGames;
    private ValueEventListener listener;
    public List<MultiplayerMatch> getLaMevaLlista() {
        return laMevaLlista;
    }

    List<MultiplayerMatch> laMevaLlista = new ArrayList<>();

    public MultiplayerMatchesProvider(){
        laMevaLlista.add(new MultiplayerMatch("-asdfasdfas", "JJ", "example@example.cat"));
    }

    public void getFromFirebase(){
        myFirebaseDBGames = GlobalInfo.getInstance().getFirebaseGames();
        Query q = myFirebaseDBGames.orderByChild("status").equalTo(MultiplayerGame.MULTIPLAYER_STATUS_PENDING);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                refreshData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Provider", "Problemes de connexio");
            }
        });
    }

    private void refreshData(DataSnapshot snapshot){
        laMevaLlista.clear();
        for (DataSnapshot gameSelected : snapshot.getChildren()){
            String key = gameSelected.getKey();
            String userName = gameSelected.child("nombre").getValue(String.class);
            String userEmail = gameSelected.child("correo").getValue(String.class);
            MultiplayerMatch mm = new MultiplayerMatch(key, userName, userEmail);
            laMevaLlista.add(mm);
        }
        adapter.notifyItemRangeChanged(0, laMevaLlista.size());
    }

    public void setAdapter(MultiplayerMatchesAdapter adapter) {
        this.adapter = adapter;
    }

    public void detach() {
        myFirebaseDBGames.removeEventListener(listener);
    }
}
