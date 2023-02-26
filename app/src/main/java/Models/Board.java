package Models;

import java.util.Random;

public class Board {
    Piece[][] board;
    int size;
    char possibleValues[] = {'A','B', 'C', 'D', 'E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public Board(int size){
        this.size = size;
        board = new Piece[size][size];
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                board[i][j] = new Piece();
            }
        }
    }
    public boolean isCellEmpty(int row, int col){

        return board[row][col] == null;
    }

    public boolean isFull(){

        return board.length == 8;
    }

    public void load(int level)
    {
        String paraula = levels.GetLevel(level);
        Random randomizer = new Random();
        char[] par = paraula.toCharArray();
        int cont = 0;
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                if(cont < par.length)
                {
                    board[i][j].setValue(par[cont]);
                }
                else
                {
                    board[i][j].setValue(possibleValues[randomizer.nextInt(possibleValues.length)]);
                }
                cont++;
            }
        }


    }

    public Piece getPiece(int row, int col)
    {
        return board[row][col];
    }



}
