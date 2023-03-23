package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.DB.DatabaseGetThePic;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.levels;

public class GameViewModel extends ViewModel {
    private MutableLiveData<Game> game = new MutableLiveData<>();
    private MutableLiveData<Integer> d = new MutableLiveData<>();



    private Context context;



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

    public void cardClicked(int row){
        Game myGame = game.getValue();
        myGame.cardClicked(row);
        game.setValue(myGame);
        d.setValue(levels.Getimage(myGame.getCurrentPlayer().getLAST_LEVEL()));
        if(myGame.win == true)
        {
            showCards();
        }
        if(myGame.equivocat == true)
        {
            showCards();
        }
        updateGameInDB();
    }





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



}
