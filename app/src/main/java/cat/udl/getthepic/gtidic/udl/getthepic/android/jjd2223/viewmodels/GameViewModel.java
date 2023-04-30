package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels;


import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.DB.DatabaseGetThePic;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.levels;

public class GameViewModel extends ViewModel {

    /***
     * Inicialització dels MutableLiveDATA
     */
    private MutableLiveData<Game> game = new MutableLiveData<>();
    private MutableLiveData<Integer> d = new MutableLiveData<>();

    /***
     * Inicialització de les instàncies de persistència
     */

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();



    private Context context;


    /***
     * Init del GameViewModel
     */
    public GameViewModel(){
        Game internalGame = new Game();
        internalGame.init();
        game.setValue(internalGame);
        showCards();
        d.setValue(levels.Getimage(game.getValue().getCurrentPlayer().getLAST_LEVEL()));

    }

    public LiveData<Game> getGame()
    {
        return game;
    }
    public LiveData<Integer> getDrawableXaxi(){
        return d;
    }

    /***
     * el cardClicked fa un set al objecte Game sobre quina carta s`ha pitjada per a tal que aquest el pugui
     * intepretar. A part va actualizant el drawable del joc i guardant dins les bases de dades el progrés
     * @param row
     */
    public void cardClicked(int row){
        Game myGame = game.getValue();
        myGame.cardClicked(row);
        game.setValue(myGame);
        d.setValue(levels.Getimage(myGame.getCurrentPlayer().getLAST_LEVEL()));
        if(myGame.win == true)
        {
            showCards();
            Toast.makeText(context,"Molt bé,! Seguent Nivell", Toast.LENGTH_SHORT).show();
        }
        if(myGame.equivocat == true)
        {
            showCards();
            Toast.makeText(context, "T'has equivocat, torna a intentar-ho", Toast.LENGTH_SHORT).show();
        }
        updateGameInDB();
        saveFireBaseDB();
    }


    /***
     * Els mètodes showCards i hideCards bàsicament tenen la unció de destapar i tapar les cartes segons el joc
     */
    private void showCards()
    {
        Game mygame = game.getValue();
        for(int i = 0; i < 8; i++)
        {
            mygame.board.getPiece(i).setGirada(true);
            mygame.board.getPiece(i).setenabled(false);
        }
        game.setValue(mygame);
        new Handler().postDelayed(() -> hideCards(),4000);
    }
    private void hideCards() {
        Game mygame = game.getValue();
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

    public void saveGameIntoDB(){
        String dbName = "GTP.db";
        DatabaseGetThePic dbRoom = Room.databaseBuilder(context, DatabaseGetThePic.class, dbName).allowMainThreadQueries().build();
        Game g = game.getValue();

        g.id = dbRoom.gameDAO().insert(g);

        String missatge = String.format("He guardat el meu joc amb id: %d", g.id );
        System.out.println(missatge);
        dbRoom.close();


    }

    public void updateGameInDB(){
        String dbName = "GTP.db";
        DatabaseGetThePic dbRoom = Room.databaseBuilder(context, DatabaseGetThePic.class, dbName).allowMainThreadQueries().build();
        Game g = game.getValue();
        dbRoom.gameDAO().update(g);
        dbRoom.close();
    }

    public void saveFireBaseDB()
    {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            DocumentReference usuarioRef = db.collection("usuarios").document(currentUser.getUid());

            usuarioRef.update("points", game.getValue().getCurrentPlayer().getPoints())
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "Valor de lastLogin actualizado correctamente"))
                    .addOnFailureListener(e -> Log.e(TAG, "Error al actualizar el valor de lastLogin", e));
            usuarioRef.update("last_level", game.getValue().getCurrentPlayer().getLAST_LEVEL())
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "Valor de lastLogin actualizado correctamente"))
                    .addOnFailureListener(e -> Log.e(TAG, "Error al actualizar el valor de lastLogin", e));
        }
    }
    //commit




}
