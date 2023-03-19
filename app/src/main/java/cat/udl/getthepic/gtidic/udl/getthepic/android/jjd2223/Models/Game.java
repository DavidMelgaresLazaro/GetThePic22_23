package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models;

import android.widget.Button;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.IAPlayer;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views.GlobalInfo;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.Player;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.HumanPlayer;

@Entity
public class Game {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public int totalCardsReversed = 0;
    public int POINTS_PER_MATCH = 10;
    public int maxPoints = -1;
    @Ignore
    private String actual = "";
    @Ignore
    private Player player1;
    @Ignore
    private Player player2;
    @Ignore
    Player currentPlayer;
    @Ignore
    Player winner;
    @Ignore
    public Board board;
    @Ignore
    public boolean win = false;
    @Ignore
    public boolean equivocat = false;

    public void init()
    {
        int boardSize = 8;
        player1 = new HumanPlayer("Spiderman \uD83D\uDD77️");
        player2 = new HumanPlayer("Starlight ⚡️");
        currentPlayer = player1;

        board = new Board(boardSize);
        board.load(getCurrentPlayer().getLAST_LEVEL());

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
        if(totalCardsReversed == levels.GetLevel(getCurrentPlayer().getLAST_LEVEL()).length())
        {
            actual = "";
            totalCardsReversed = 0;
            equivocat = true;
            changeTurn();

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
        if(actual.equals(levels.GetLevel(getCurrentPlayer().getLAST_LEVEL()))) {
            getCurrentPlayer().setLAST_LEVEL(getCurrentPlayer().getLAST_LEVEL() + 1);
            board.load(getCurrentPlayer().getLAST_LEVEL());
            actual = "";
            totalCardsReversed = 0;
            win = true;
            getCurrentPlayer().addPoints(POINTS_PER_MATCH);
        }


    }



}