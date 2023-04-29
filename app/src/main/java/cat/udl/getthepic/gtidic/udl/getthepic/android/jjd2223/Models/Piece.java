package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models;


public class Piece {

    /***
     * Dins Piece tenim tres variables.Piece representa cada una de les cartess dins del board quan juguem.
     * value : Representa quina lletra te la carta
     * girada : Si la carta esta girada TRUE ,si no , FALSE.
     * enabled : Si una carta esta enabled = true deixarà pitjar-la , en cas contrari nomes veurer-la.
     *
     * Tots els mètodes son getters i setters per tant no els comentarem
     */
    private char value;
    public boolean girada;
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

