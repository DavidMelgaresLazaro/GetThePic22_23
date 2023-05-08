package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels;





import android.os.Handler;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerGame;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.levels;


public class MultiplayerViewModel extends ViewModel {

    /***
     * Inicialització dels MutableLiveDATA
     */
    private MutableLiveData<MultiplayerGame> game = new MutableLiveData<>();
    private MutableLiveData<Integer> d = new MutableLiveData<>();

    private MutableLiveData<String> oponentName = new MutableLiveData<>();


    /***
     * Inicialització de les instàncies de persistència
     */



    /***
     * Init del GameViewModel
     */
    public MultiplayerViewModel(){


        MultiplayerGame internalGame = new MultiplayerGame();
        internalGame.init();
        game.setValue(internalGame);
        showCards();
        d.setValue(levels.Getimage(game.getValue().player1.getLAST_LEVEL()));

    }

    public LiveData<MultiplayerGame> getGame()
    {
        return game;
    }
    public LiveData<Integer> getDrawableXaxi(){
        return d;
    }
    public LiveData<String> getoponentName(){return oponentName;}

    /***
     * el cardClicked fa un set al objecte Game sobre quina carta s`ha pitjada per a tal que aquest el pugui
     * intepretar. A part va actualizant el drawable del joc i guardant dins les bases de dades el progrés
     * @param row
     */
    public void cardClicked(int row){
        MultiplayerGame myGame = game.getValue();
        myGame.cardClicked(row);
        game.setValue(myGame);
        d.setValue(levels.Getimage(myGame.player1.getLAST_LEVEL()));
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
        MultiplayerGame mygame = game.getValue();
        for(int i = 0; i < 8; i++)
        {
            mygame.board.getPiece(i).setGirada(true);
            mygame.board.getPiece(i).setenabled(false);
        }
        game.setValue(mygame);
        new Handler().postDelayed(() -> hideCards(),6000);
    }
    private void hideCards() {
        MultiplayerGame mygame = game.getValue();
        for(int i = 0; i < 8; i ++ )
        {
            mygame.board.getPiece(i).setGirada(false);
            mygame.board.getPiece(i).setenabled(true);
        }
        game.setValue(mygame);
    }




}
