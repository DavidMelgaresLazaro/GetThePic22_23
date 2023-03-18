package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models;

public class Piece {
    private char value;
    private boolean girada;
    private boolean enabled;
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

    public void setGirada(boolean girada) {
        this.girada = girada;
    }
    public boolean getGirada()
    {
        return this.girada;
    }
    public void setenabled(boolean enabled) {
        this.enabled = enabled;
    }
    public boolean getenabled()
    {
        return this.enabled;
    }
}
