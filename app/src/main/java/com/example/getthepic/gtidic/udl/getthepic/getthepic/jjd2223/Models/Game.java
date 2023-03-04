package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models;

import android.widget.Button;

import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.GlobalInfo;
import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models.Player.Player;

public class Game {
    private int totalCardsReversed = 0;
    private String actual = "";
    Player currentPlayer;
    Player winner;
    public Board board;

    public void init()
    {
        int boardSize = 8;

        board = new Board(boardSize);
        board.load(GlobalInfo.getInstance().getLastLevel());
    }

    public void cardClicked(Button button, int row){
        actual += Character.toString(board.getPiece(row).getValue());
        totalCardsReversed++;
        if(totalCardsReversed == levels.GetLevel(GlobalInfo.getInstance().getLastLevel()).length() )
        {
            //TODO de restart la GameActivity
        }
        mirar();
    }

    public int getTotalCardsReversed()
    {
        return totalCardsReversed;
    }

    public void mirar()
    {
        if(actual.equals(levels.GetLevel(GlobalInfo.getInstance().getLastLevel()))) {
            GlobalInfo.getInstance().UpdateLastLevel(GlobalInfo.getInstance().getLastLevel() + 1);
            board.load(GlobalInfo.getInstance().getLastLevel());
            actual = "";
        }
    }



}