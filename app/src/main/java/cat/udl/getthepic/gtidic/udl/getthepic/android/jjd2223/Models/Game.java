package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models;

import android.widget.Button;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views.GlobalInfo;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.Player;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.HumanPlayer;

public class Game {
    private int totalCardsReversed = 0;
    protected int POINTS_PER_MATCH = 10;
    protected int maxPoints = -1;
    private String actual = "";
    public Button viewbutton = null;
    private Player player1;
    private Player player2;
    Player currentPlayer;
    Player winner;
    public Board board;
    public boolean win = false;
    public boolean equivocat = false;

    public void init()
    {
        int boardSize = 8;

        board = new Board(boardSize);
        board.load(GlobalInfo.getInstance().getLastLevel());


        player1 = new HumanPlayer("Spiderman \uD83D\uDD77️");
        player2 = new HumanPlayer("Starlight ⚡️");
        currentPlayer = player1;
    }
    protected void changeTurn(){
        currentPlayer = currentPlayer == player1 ? player2 : player1;

    }


    public Player getCurrentPlayer(){ return currentPlayer; }

    public void cardClicked(int row){
        equivocat = false;
        actual += Character.toString(board.getPiece(row).getValue());
        totalCardsReversed++;
        checkifwin();
        if(totalCardsReversed == levels.GetLevel(GlobalInfo.getInstance().getLastLevel()).length())
        {
            actual = "";
            totalCardsReversed = 0;
            equivocat = true;

        }

        board.getPiece(row).setGirada(true);



    }

    public int getTotalCardsReversed()
    {
        return totalCardsReversed;
    }


    public void checkifwin()
    {
        win = false;
        if(actual.equals(levels.GetLevel(GlobalInfo.getInstance().getLastLevel()))) {
            GlobalInfo.getInstance().UpdateLastLevel(GlobalInfo.getInstance().getLastLevel() + 1);
            board.load(GlobalInfo.getInstance().getLastLevel());
            actual = "";
            totalCardsReversed = 0;
            win = true;


        }


    }



}