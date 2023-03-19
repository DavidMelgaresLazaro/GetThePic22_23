package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player;


import androidx.room.PrimaryKey;

public class HumanPlayer extends Player {


    protected String name;


    @Override
    public String getName() {
        return name;
    }
    public HumanPlayer(String name){
        this.name = name;
    }
}


