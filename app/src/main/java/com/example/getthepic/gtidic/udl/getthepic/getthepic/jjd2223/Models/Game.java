package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models;

import android.view.View;
import android.widget.Button;

import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.GlobalInfo;
import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models.Player.Player;
import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models.Player.HumanPlayer;

public class Game {
    private int totalCardsReversed = 0;
    private String actual = "";
    public Button viewbutton = null;
    private Player player1;
    private Player player2;
    Player currentPlayer;
    Player winner;
    public Board board;
    public boolean win = false;

    public void init()
    {
        int boardSize = 8;

        board = new Board(boardSize);
        board.load(GlobalInfo.getInstance().getLastLevel());


        player1 = new HumanPlayer("Spiderman \uD83D\uDD77️");
        player2 = new HumanPlayer("Starlight ⚡️");
        currentPlayer = player1;
    }


    public Player getCurrentPlayer(){ return currentPlayer; }

    public void cardClicked(View button,int row){
        actual += Character.toString(board.getPiece(row).getValue());
        totalCardsReversed++;
        checkifwin();
        if(totalCardsReversed == levels.GetLevel(GlobalInfo.getInstance().getLastLevel()).length())
        {
            actual = "";
            totalCardsReversed = 0;
            //TODO de restart la GameActivity
        }
        viewbutton = (Button) button;

        currentPlayer = currentPlayer == player1 ? player2 : player1;
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
            //board.load(GlobalInfo.getInstance().getLastLevel());
            actual = "";
            totalCardsReversed = 0;
            win = true;
        }
    }



}