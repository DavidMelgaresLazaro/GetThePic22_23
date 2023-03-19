package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player;

import android.content.Context;
import android.os.Handler;

import java.util.Random;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.levels;

public class IAPlayer extends Player{

    int algorytmType;
    Random random;
    int i;
    Context context;
    String actual = "";
    Game game;

    public void init()
    {
        game = new Game();
        game.init();
    }

    public String getName() {
        return "super IA ⭐️";
    }

    public void jugar()
    {
        String s;
        while(actual != levels.GetLevel(game.getCurrentPlayer().getLAST_LEVEL()))
        {
            actual = "";
            for(int i = 0 ; i < game.getCurrentPlayer().getLAST_LEVEL(); i ++)
            {
                s = game.board.getPiece(random.nextInt(8)).toString();
                if(s.contains(game.board.getPiece(random.nextInt(8)).toString()));
                {
                    s = "";
                    s = game.board.getPiece(random.nextInt(8)).toString();
                }
                actual = actual + s;
                s = "";
            }
            new Handler().postDelayed(() -> res(),40);
            System.out.println("Actual" + actual);
            }

    }

    public void setContext(Context context)
    {
        this.context = context;
    }
    public void res()
    {
    }


    }
