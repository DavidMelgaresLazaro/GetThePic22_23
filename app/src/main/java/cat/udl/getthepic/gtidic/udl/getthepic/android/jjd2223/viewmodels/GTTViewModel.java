package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels;


import static android.content.ContentValues.TAG;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.Application;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.GTimeTrial;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.levels;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views.ResultTT;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views.menu;

public class GTTViewModel extends ViewModel {


    private static CountDownTimer countDownTimer;


    /***
     * Inicialització dels MutableLiveDATA
     */

    private MutableLiveData<GTimeTrial> game = new MutableLiveData<>();
    private MutableLiveData<Integer> d = new MutableLiveData<>();

    private MutableLiveData<Integer> Time = new MutableLiveData<>();

    /***
     * Inicialització de les instàncies de persistència
     */

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth1 = FirebaseAuth.getInstance();


    public int lastLevel = 0;

    private static Context context;


    /***
     * Init del GameViewModel
     */
    public GTTViewModel(){
        GTimeTrial internalGame = new GTimeTrial();
        internalGame.init();
        game.setValue(internalGame);
        showCards();
        d.setValue(levels.Getimage(Arrays.asList(levels.levelsP).indexOf(levels.GetRandomLevelStr())));
        startTimer();


    }

    public LiveData<GTimeTrial> getGame()
    {
        return game;
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
        d.setValue(levels.Getimage(Arrays.asList(levels.levelsP).indexOf(levels.GetRandomLevelStr())));
        GTimeTrial myGame = game.getValue();
        myGame.cardClicked(row);
        game.setValue(myGame);



        if(myGame.win == true)
        {

            showCards();
            lastLevel = myGame.getLevelsTotal();
            d.setValue(levels.Getimage(Arrays.asList(levels.levelsP).indexOf(levels.GetRandomLevelStr())));

        }
        if(myGame.equivocat == true)
        {
            showCards();

        }

        saveFireBaseDB();
    }


    /***
     * Els mètodes showCards i hideCards bàsicament tenen la unció de destapar i tapar les cartes segons el joc
     */
    private void showCards()
    {
        GTimeTrial mygame = game.getValue();
        for(int i = 0; i < 8; i++)
        {
            mygame.board.getPiece(i).setGirada(true);
            mygame.board.getPiece(i).setenabled(false);
        }
        game.setValue(mygame);
        new Handler().postDelayed(() -> hideCards(),4000);
    }
    private void hideCards() {
        GTimeTrial mygame = game.getValue();
        for(int i = 0; i < 8; i ++ )
        {
            mygame.board.getPiece(i).setGirada(false);
            mygame.board.getPiece(i).setenabled(true);
        }
        game.setValue(mygame);
    }

    public void setContext(Context context) {
        this.context = context;
    }



    private void startTimer()
    {

        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {

                int segundosRestantes = (int) millisUntilFinished / 1000;
                Time.setValue(segundosRestantes);
            }

            public void onFinish() {
                // Código que se ejecuta cuando la cuenta atrás ha terminado
            }
        }.start();
    }

    @Override
    protected void onCleared() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }


    public interface FirebaseSaveCallback {
        void onSaveComplete(int levelsTotal);
    }


    public void saveFirebaseDB(FirebaseSaveCallback callback) {
        // Tu código para guardar en Firebase Firestore

        // Después de guardar en Firebase, obtén el valor de levelsTotal y llama a la interfaz de devolución de llamada
        int levelsTotal = game.getValue().getLevelsTotal();
        callback.onSaveComplete(levelsTotal);
    }


    public void saveFireBaseDB() {
        FirebaseUser currentUser = mAuth1.getCurrentUser();
        if (currentUser != null) {
            DocumentReference usuarioRefTT = db.collection("usuarios").document(currentUser.getUid());

            usuarioRefTT.update("Levels_TT", game.getValue().getLevelsTotal())
                    .addOnSuccessListener(aVoid -> {
                        Log.d(TAG, "Last_level correct");


                    })
                    .addOnFailureListener(e -> Log.e(TAG, "Last_level incorrect", e));
        }


    }
}