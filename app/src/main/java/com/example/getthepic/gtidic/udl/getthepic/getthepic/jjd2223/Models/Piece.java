package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models;

public class Piece {
    private char value;
    public boolean girada;
    public void setValue(char lletra)
    {
        value = lletra;
    }
    public char getValue()
    {
        return value;
    }

    public boolean giradareturn1()
    {
        if(girada == true)
        {
            return true;
        }
        return false;
    }
    public int giradareturn()
    {
        if(girada == true)
        {
            return 60;
        }
        return 0;
    }
}
