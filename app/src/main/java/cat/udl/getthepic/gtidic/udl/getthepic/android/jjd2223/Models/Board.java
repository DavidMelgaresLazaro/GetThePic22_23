package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models;

import java.util.Random;

public class Board {
    private Piece[] board;
    private int size;
    private char possibleValues[] = {'A','B', 'C', 'D', 'E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public Board(int size){
        this.size = size;
        board = new Piece[size];
        for (int i = 0; i<size; i++){
                board[i] = new Piece();
        }
    }

    /***
     * En un futur es podrien utilitzar, per aixo no els borrem.

     */

    /*public boolean isCellEmpty(int row){

        return board[row] == null;
    }

    public boolean isFull(){

        return board.length == 8;
    }*/

    public void load(int level)
    {
        String paraula = levels.GetLevel(level);
        Random randomizer = new Random();
        char[] par = paraula.toCharArray();
        int cont = 0;
        for (int i = 0; i < size; i++){
                if(cont < par.length)
                {
                    board[i].setValue(par[cont]);
                }
                else
                {
                    board[i].setValue(possibleValues[randomizer.nextInt(possibleValues.length)]);
                }
                cont++;
        }
        Random rand = new Random();

        for (int i = 0; i < board.length; i++) {
            int randomIndexToSwap = rand.nextInt(board.length);
            Piece temp = board[randomIndexToSwap];
            board[randomIndexToSwap] = board[i];
            board[i] = temp;
        }
    }

    public Piece getPiece(int row)
    {
        return board[row];
    }



}
