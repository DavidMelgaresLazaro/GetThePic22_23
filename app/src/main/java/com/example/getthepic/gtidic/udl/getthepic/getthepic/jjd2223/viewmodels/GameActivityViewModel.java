package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.viewmodels;

import android.os.Handler;

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
        showCards();
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
            mygame.board.getPiece(i).girada = true;
        }
        game.setValue(mygame);
        new Handler().postDelayed(() -> hideCards(),4000);
    }
    private void hideCards() {
        Game mygame = game.getValue();
        for(int i = 0; i < 8; i ++ )
        {
            mygame.board.getPiece(i).girada = false;
        }
        game.setValue(mygame);
    }


}
