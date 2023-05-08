package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models;


import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.Player;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.HumanPlayer;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.GlobalInfo;

public class MultiplayerGame {

    public int totalCardsReversed = 0;
    public int POINTS_PER_MATCH = 10;
    public int maxPoints = -1;

    private String actual = "";

    public Player player1;


    public Board board;

    public boolean win = false;

    public boolean equivocat = false;

    private FirebaseAuth mAuth;

    private FirebaseUser user;

    public void init()
    {
        int boardSize = 8;
        UpdateUser();
        player1 = new HumanPlayer(user.getEmail());
        player1.setLAST_LEVEL(GlobalInfo.getInstance().getLast_level());
        player1.setPoints(GlobalInfo.getInstance().getLast_points());

        board = new Board(boardSize);
        board.load(player1.getLAST_LEVEL());

    }



    public void cardClicked(int row){
        equivocat = false;
        actual += Character.toString(board.getPiece(row).getValue());
        totalCardsReversed++;
        checkifwin();
        if(totalCardsReversed == levels.GetLevel(player1.getLAST_LEVEL()).length())
        {
            actual = "";
            totalCardsReversed = 0;
            equivocat = true;

        }
        board.getPiece(row).setGirada(true);
    }



    public void checkifwin()
    {
        win = false;
        if(actual.equals(levels.GetLevel(player1.getLAST_LEVEL()))) {
            player1.setLAST_LEVEL(player1.getLAST_LEVEL() + 1);
            GlobalInfo.getInstance().setLast_level(player1.getLAST_LEVEL() + 1);
            board.load(player1.getLAST_LEVEL());
            actual = "";
            totalCardsReversed = 0;
            win = true;
            player1.addPoints(POINTS_PER_MATCH);
        }


    }

    private void UpdateUser()
    {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }



}