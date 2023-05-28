package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.Player;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.HumanPlayer;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.GlobalInfo;

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
    @Ignore
    private FirebaseAuth mAuth;
    @Ignore
    private FirebaseUser user;
    @Ignore
    public boolean jocacabat;

    public void init()
    {
        int boardSize = 8;
        UpdateUser();
        player1 = new HumanPlayer(user.getEmail());
        player1.setLAST_LEVEL(GlobalInfo.getInstance().getLast_level());
        player1.setPoints(GlobalInfo.getInstance().getLast_points());
        //player2 = new HumanPlayer("Starlight ⚡️");
        //currentPlayer = player1;

        board = new Board(boardSize);
        board.load(player1.getLAST_LEVEL());

    }
    protected void changeTurn(){
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }


    public Player getCurrentPlayer(){
        //return currentPlayer;
        return player1;
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
            changeTurn();

        }
        board.getPiece(row).setGirada(true);
    }

    public int getTotalCardsReversed()
    {
        return totalCardsReversed;
    }
    public int getPOINTS_PER_MATCH()
    {
        return POINTS_PER_MATCH;
    }
    public int getMaxPoints()
    {
        return maxPoints;
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

            if(levels.imagesresources.length == player1.getLAST_LEVEL())
            {
                jocacabat = true;
            }
        }
    }
    private void UpdateUser()
    {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

}