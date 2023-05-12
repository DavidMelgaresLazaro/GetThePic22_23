package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels;





import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Board;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerGame;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.levels;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.GlobalInfo;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views.Multiplayer;


public class MultiplayerViewModel extends ViewModel {

    /***
     * Inicialització dels MutableLiveDATA
     */
    public int segundosRestantes;
    public String selfName;
    public String oponentName;
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

        selfName = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        MultiplayerGame internalGame = new MultiplayerGame();
        internalGame.init();
        multiplayergame.setValue(internalGame);
        showCards();
        d.setValue(levels.Getimage(internalGame.nivell));
        startTimer();

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


    private void startTimer()
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
    }

    private void enableFirebaseDBv2() {
        myFirebaseDBReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int oponentPoints = dataSnapshot.child("oponentPoints").getValue(Integer.class);
                String oponentName = dataSnapshot.child("oponentName").getValue(String.class);
                System.out.println(oponentName);
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
        String selfName = this.selfName;
        oponentName = "X";
        myFirebaseDBReference = myFirebaseDBGames.child(key);


        Map<String, Object> data = new HashMap<>();
        data.put("selfPoints", 0);
        data.put("status", MultiplayerGame.MULTIPLAYER_STATUS_PENDING);
        data.put("selfName", selfName);
        data.put("oponentPoints",0);
        data.put("oponentName",oponentName);
        myFirebaseDBReference.setValue(data);

        enableFirebaseDBv2();
        updateFirebaseDBv2();
    }

    /**
     * Connecta amb un game de firebase el bloqueja com a ja matchat i comença el joc
     */
    public void multiplayerConnect(String gameKey) {
        DatabaseReference games = GlobalInfo.getInstance().getFirebaseGames();

//        setting my config
        myFirebaseDBReference = games.child(gameKey);

        myFirebaseDBReference.child("status").setValue(MultiplayerGame.MULTIPLAYER_STATUS_MATCHED);
        // TODO millora: idealment hauriem d'agafar el turn del Firebase
        //multiplayergame.getValue().setCurrentPlayerMultiplayer(MultiplayerGame.MULTIPLAYER_TYPE_CREATE);

        enableFirebaseDBv2();
    }

    private void updateFirebaseDBv2() {
        if (myFirebaseDBReference != null){
            MultiplayerGame g = multiplayergame.getValue();
            myFirebaseDBReference.child("selfPoints").setValue(g.maxPoints);
        }

    }




}
