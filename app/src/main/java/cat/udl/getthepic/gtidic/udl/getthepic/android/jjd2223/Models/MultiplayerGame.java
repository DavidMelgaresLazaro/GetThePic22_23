package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models;


public class MultiplayerGame {

    public int totalCardsReversed = 0;
    public int POINTS_PER_MATCH = 10;
    public int maxPoints = 0;
    public int maxPointsOponent = 0;

    public String oponentName = "";

    private String actual = "";


    public Board board;

    public boolean win = false;

    public boolean equivocat = false;

    public int nivell;

    public static final int MULTIPLAYER_STATUS_PENDING = 1;
    public static final int MULTIPLAYER_STATUS_MATCHED = 2;
    public static final int MULTIPLAYER_TYPE_CONNECT = 2;

    public static final String MULTIPLAYER_KEY = "multiplayer-key";
    public static final String MULTIPLAYER_GAME_KEY = "multiplayer-game-key";

    public void init()
    {
        nivell = levels.ReturnRandomLevel();
        int boardSize = 8;


        board = new Board(boardSize);
        board.load(nivell);

    }

    public void cardClicked(int row){
        equivocat = false;
        actual += Character.toString(board.getPiece(row).getValue());
        totalCardsReversed++;
        checkifwin();
        if(totalCardsReversed == levels.GetLevel(nivell).length())
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
        if(actual.equals(levels.GetLevel(nivell))) {
            nivell = levels.ReturnRandomLevel();
            board.load(nivell);
            actual = "";
            totalCardsReversed = 0;
            win = true;
            maxPoints = maxPoints + POINTS_PER_MATCH;
        }


    }

}