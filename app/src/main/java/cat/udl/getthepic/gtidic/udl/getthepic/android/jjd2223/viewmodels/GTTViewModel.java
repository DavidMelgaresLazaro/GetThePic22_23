package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels;


import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.Application;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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

    /***
     * Inicialització de les instàncies de persistència
     */

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth1 = FirebaseAuth.getInstance();



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


    }

    public LiveData<GTimeTrial> getGame()
    {
        return game;
    }
    public LiveData<Integer> getDrawableXaxi(){
        return d;
    }

    /***
     * Propiedad LiveData de tipo String en tu ViewModel que representará el tiempo restante.
     */
    private static MutableLiveData<String> timeLeftLiveData = new MutableLiveData<>("00:00");

    public LiveData<String> getTimeLeftLiveData() {
        return timeLeftLiveData;
    }

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



    public static void startTimer() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));

                String timeLeftString = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

                timeLeftLiveData.postValue(timeLeftString);
            }

            @Override
            public void onFinish() {
                timeLeftLiveData.postValue("00:00");
                Intent intent = new Intent(context, ResultTT.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                // Cierra la actividad actual
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
        }.start();
    }

    @Override
    protected void onCleared() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }



    public void saveFireBaseDB()
    {
        FirebaseUser currentUser = mAuth1.getCurrentUser();
        if (currentUser != null) {
            DocumentReference usuarioRefTT = db.collection("usuarios").document(currentUser.getUid());

            usuarioRefTT.update("Levels_TT", game.getValue().getLevelsTotal())
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "Last_level correct"))
                    .addOnFailureListener(e -> Log.e(TAG, "Last_level incorrect", e));
        }
    }





}




