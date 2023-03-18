package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;

public class GameActivityViewModel extends ViewModel {
    private MutableLiveData<Game> game = new MutableLiveData<>();


    public GameActivityViewModel(){
        Game internalGame = new Game();
        internalGame.init();
        game.setValue(internalGame);
    }

    public LiveData<Game> getGame()
    {
        return game;
    }


    public void cardClicked(View button,int row){
        Game myGame = game.getValue();
        myGame.cardClicked(button,row);
        game.setValue(myGame);

    }


}
