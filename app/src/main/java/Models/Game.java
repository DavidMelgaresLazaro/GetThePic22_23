package Models;

import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.GlobalInfo;

import Models.Player.Player;

public class Game {
    int LEVEL = GlobalInfo.LAST_LEVEL;
    Player currentPlayer;
    Player winner;
    Board board;

    public void init()
    {
        int boardSize = 8;

        board = new Board(4);
        board.load(LEVEL);
    }
}