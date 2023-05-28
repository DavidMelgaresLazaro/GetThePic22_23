package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player;



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


