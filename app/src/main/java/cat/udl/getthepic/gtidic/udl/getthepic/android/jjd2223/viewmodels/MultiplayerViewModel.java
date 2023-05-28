package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels;





import static android.content.ContentValues.TAG;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;


import java.util.HashMap;
import java.util.Map;


import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerGame;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.levels;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.GlobalInfo;


public class MultiplayerViewModel extends ViewModel {

    /***
     * Inicialització dels MutableLiveDATA
     */
    public int segundosRestantes;
    public boolean oponent = false;
    public String selfName;

    private String gameKeyy;
    public String oponentName,selfEmail;
    private FirebaseAuth mAuth;

    protected String myClassTag = this.getClass().getSimpleName();

    private DatabaseReference myRef;
    private DatabaseReference myFirebaseDBReference;

    private Integer myMultiplayerPlayerType;

    private MutableLiveData<MultiplayerGame> multiplayergame = new MutableLiveData<>();
    private MutableLiveData<Integer> d = new MutableLiveData<>();


    private MutableLiveData<Integer> Time = new MutableLiveData<>();


    /***
     * Inicialització de les instàncies de persistència
     */



    /***
     * Init del GameViewModel
     */
    public MultiplayerViewModel(){

        selfName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        selfEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        MultiplayerGame internalGame = new MultiplayerGame();
        internalGame.init();
        multiplayergame.setValue(internalGame);

    }

    public LiveData<MultiplayerGame> getMultiplayerGame()
    {
        return multiplayergame;
    }
    public LiveData<Integer> getDrawableXaxi(){
        return d;
    }

    public LiveData<Integer> getTime(){return Time;}

    /***
     * el cardClicked fa un set al objecte Game sobre quina carta s`ha pitjada per a tal que aquest el pugui
     * intepretar. A part va actualizant el drawable del joc i guardant dins les bases de dades el progrés
     * @param row
     */
    public void cardClicked(int row){
        MultiplayerGame myGame = multiplayergame.getValue();
        myGame.cardClicked(row);
        multiplayergame.setValue(myGame);
        d.setValue(levels.Getimage(multiplayergame.getValue().nivell));
        if(myGame.win == true)
        {
            if(oponent = true) {
                updateFirebaseDBv2Oponent();
            }else {
                updateFirebaseDBv2();
            }
            showCards();
        }
        if(myGame.equivocat == true)
        {
            showCards();
        }
    }


    /***
     * Els mètodes showCards i hideCards bàsicament tenen la unció de destapar i tapar les cartes segons el joc
     */
    private void showCards()
    {
        MultiplayerGame mygame = multiplayergame.getValue();
        for(int i = 0; i < 8; i++)
        {
            mygame.board.getPiece(i).setGirada(true);
            mygame.board.getPiece(i).setenabled(false);
        }
        multiplayergame.setValue(mygame);
        new Handler().postDelayed(() -> hideCards(),6000);
    }
    private void hideCards() {
        MultiplayerGame mygame = multiplayergame.getValue();
        for(int i = 0; i < 8; i ++ )
        {
            mygame.board.getPiece(i).setGirada(false);
            mygame.board.getPiece(i).setenabled(true);
        }
        multiplayergame.setValue(mygame);
    }


    private void startTimerOponent()
    {
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                 segundosRestantes = (int) millisUntilFinished / 1000;
                 Time.setValue(segundosRestantes);
            }

            public void onFinish() {
                // Código que se ejecuta cuando la cuenta atrás ha terminado
            }
        }.start();
        showCards();
        d.setValue(levels.Getimage(multiplayergame.getValue().nivell));
    }

    private void startTimer() {
        DatabaseReference games = GlobalInfo.getInstance().getFirebaseGames();
        myRef = games.child(gameKeyy);
        myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                        int state = dataSnapshot.child("status").getValue(Integer.class);
                        if (state == MultiplayerGame.MULTIPLAYER_STATUS_MATCHED) {
                            new CountDownTimer(30000, 1000) {
                                public void onTick(long millisUntilFinished) {
                                    segundosRestantes = (int) millisUntilFinished / 1000;
                                    Time.setValue(segundosRestantes);
                                }

                                public void onFinish() {
                                    // Code that executes when the countdown finishes
                                }
                            }.start();
                            showCards();
                            d.setValue(levels.Getimage(multiplayergame.getValue().nivell));
                        }

                    }
                    @Override
                    public void onCancelled(@NotNull DatabaseError error) { // Failed to read value
                        Log.w(myClassTag, "Failed to read value.", error.toException());
                    }
                });

    }

    private void enableFirebaseDBv2() {
        myFirebaseDBReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int oponentPoints = dataSnapshot.child("oponentPoints").getValue(Integer.class);
                String oponentName = dataSnapshot.child("oponentName").getValue(String.class);
                MultiplayerGame g = multiplayergame.getValue();
                g.oponentName = oponentName;
                g.maxPointsOponent = oponentPoints;
                //multiplayergame.setCurrentPlayerMultiplayer(multiplayerTurn);
                multiplayergame.setValue(g);
            }
            @Override
            public void onCancelled(@NotNull DatabaseError error) { // Failed to read value
                Log.w(myClassTag, "Failed to read value.", error.toException());
            }
        });
    }


    /**
     * Crea un game a firebase perquè es connecti algun altre player i l'inicialitza
     */
    public void multiplayerCreate() {
        DatabaseReference myFirebaseDBGames = GlobalInfo.getInstance().getFirebaseGames();
        String key = myFirebaseDBGames.push().getKey();
        this.gameKeyy = key;
        String selfName = this.selfName;
        oponentName = "X";
        myFirebaseDBReference = myFirebaseDBGames.child(key);


        Map<String, Object> data = new HashMap<>();
        data.put("selfPoints", 0);
        data.put("status", MultiplayerGame.MULTIPLAYER_STATUS_PENDING);
        data.put("selfName", GlobalInfo.getInstance().getSelfName());
        data.put("oponentPoints",0);
        data.put("oponentName","");
        data.put("selfEmail",selfEmail);
        myFirebaseDBReference.setValue(data);

        enableFirebaseDBv2();
        updateFirebaseDBv2();
        startTimer();
    }

    /**
     * Connecta amb un game de firebase el bloqueja com a ja matchat i comença el joc
     */
    public void multiplayerConnect(String gameKey) {
        DatabaseReference games = GlobalInfo.getInstance().getFirebaseGames();
//        setting my config
        myMultiplayerPlayerType = MultiplayerGame.MULTIPLAYER_TYPE_CONNECT;
        myFirebaseDBReference = games.child(gameKey);
        oponent = true;

        myFirebaseDBReference.child("status").setValue(MultiplayerGame.MULTIPLAYER_STATUS_MATCHED);
        myFirebaseDBReference.child("oponentName").setValue(GlobalInfo.getInstance().getSelfName());

        MultiplayerGame mygame = multiplayergame.getValue();
        mygame.oponent = true;
        multiplayergame.setValue(mygame);

        enableFirebaseDBv2Oponent();
        startTimerOponent();
    }

    private void updateFirebaseDBv2() {
        if (myFirebaseDBReference != null){
            MultiplayerGame g = multiplayergame.getValue();
            myFirebaseDBReference.child("selfPoints").setValue(g.maxPoints);
        }

    }
    private void updateFirebaseDBv2Oponent() {
        if (myFirebaseDBReference != null){
            MultiplayerGame g = multiplayergame.getValue();
            myFirebaseDBReference.child("oponentPoints").setValue(g.maxPoints);
        }

    }

    private void enableFirebaseDBv2Oponent() {
        myFirebaseDBReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int selfPoints = dataSnapshot.child("selfPoints").getValue(Integer.class);
                String selfName = dataSnapshot.child("selfName").getValue(String.class);
                MultiplayerGame g = multiplayergame.getValue();
                g.oponentName = selfName;
                g.maxPointsOponent = selfPoints;
                multiplayergame.setValue(g);
            }
            @Override
            public void onCancelled(@NotNull DatabaseError error) { // Failed to read value
                Log.w(myClassTag, "Failed to read value.", error.toException());
            }
        });
    }
}
