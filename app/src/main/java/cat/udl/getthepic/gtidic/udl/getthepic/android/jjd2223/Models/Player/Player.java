package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views.GlobalInfo;

public abstract class Player {
    protected int points = 0;

    protected int LAST_LEVEL = 0;

    public abstract String getName();

    public void addPoints(int pointsToAdd){
        points += pointsToAdd;
    }

    public int getPoints(){
        return points;
    }

    public int getLAST_LEVEL() {
        return LAST_LEVEL;
    }
    public void setLAST_LEVEL(int LAST_LEVEL) {
        this.LAST_LEVEL = LAST_LEVEL;
    }

    public void cambiarNom(String name)
    {
        cambiarNom(name);
    }
    public void setPoints(int points)
    {
        this.points = points;
    }
}

