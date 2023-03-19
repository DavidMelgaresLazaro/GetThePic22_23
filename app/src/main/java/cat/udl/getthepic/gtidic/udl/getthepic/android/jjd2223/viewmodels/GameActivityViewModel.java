package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;

public class GameActivityViewModel extends ViewModel {
    private MutableLiveData<Game> game = new MutableLiveData<>();

    private Context context;


    public GameActivityViewModel(){
        Game internalGame = new Game();
        internalGame.init();
        game.setValue(internalGame);
        showCards();
        System.out.println("Adalt");
        //drawable = ContextCompat.getDrawable(context, R.drawable.nissan);
        System.out.println("Abaix");
    }

    public LiveData<Game> getGame()
    {
        return game;
    }


    public void cardClicked(int row){
        Game myGame = game.getValue();
        myGame.cardClicked(row);
        game.setValue(myGame);

        if(myGame.win == true)
        {
            showCards();
        }
        if(myGame.equivocat == true)
        {
            showCards();
        }
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

    /*public Drawable getImage()
    {
        if(drawable != null)
        {
            return drawable;
        }
        return  this.context.getResources().getDrawable(R.drawable.logo);
    }*/


}
