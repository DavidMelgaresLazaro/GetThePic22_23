package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player;

public abstract class Player {
    private int points = 0;

    private int LAST_LEVEL = 0;

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
}

