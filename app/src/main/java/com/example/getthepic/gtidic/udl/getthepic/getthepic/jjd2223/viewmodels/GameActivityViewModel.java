package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models.Game;

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


    public void cardClicked(int row){
        Game myGame = game.getValue();
        myGame.cardClicked(row);
        game.setValue(myGame);

    }


}
