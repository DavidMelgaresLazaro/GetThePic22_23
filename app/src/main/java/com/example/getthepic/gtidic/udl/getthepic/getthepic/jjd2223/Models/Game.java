package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models;

import android.widget.Button;

import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.GlobalInfo;
import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models.Player.Player;

public class Game {
    int LEVEL = GlobalInfo.getInstance().getLastLevel();
    private int totalCardsReversed = 0;
    private String actual
    Player currentPlayer;
    Player winner;
    public Board board;

    public void init()
    {
        int boardSize = 8;

        board = new Board(boardSize);
        board.load(LEVEL);
    }

    public void cardClicked(Button button, int row){
        Piece p = board.getPiece(row);
        button.setText(Character.toString(p.getValue()));
        totalCardsReversed++;
        mirar();
    }

    public int getTotalCardsReversed()
    {
        return totalCardsReversed;
    }

    public void mirar()
    {
        if
    }



}