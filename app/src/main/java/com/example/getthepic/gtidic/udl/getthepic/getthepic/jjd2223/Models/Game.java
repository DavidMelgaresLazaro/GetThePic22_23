package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models;

import android.widget.Button;

import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models.Player.Player;
import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.views.GlobalInfo;

public class Game {
    private int totalCardsReversed = 0;
    private String actual = "";
    public Button viewbutton = null;
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

    }

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