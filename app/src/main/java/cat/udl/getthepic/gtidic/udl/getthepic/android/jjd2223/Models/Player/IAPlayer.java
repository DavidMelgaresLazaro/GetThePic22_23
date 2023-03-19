package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.util.Random;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Board;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Piece;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.levels;

public class IAPlayer extends Player{

    int algorytmType;

    Context context;
    Game game;

    public void init(Context context)
    {
        this.context = context;
        game = new Game();
        game.init();
    }

    public String getName() {
        return "super IA ⭐️";
    }

    public void jugar()
    {
        Random random = new Random();
        String actual = "";
        Piece j;
        while(!actual.equals(levels.GetLevel(game.getCurrentPlayer().LAST_LEVEL)))
        {
            actual = "";
            for(int i = 0; i < 5 ; i++)
            {
               j = game.board.getPiece(random.nextInt(8));
               if(j.girada)
               {
                   while(j.girada){
                       j = game.board.getPiece(random.nextInt(8));
                   }
               }
               actual = actual + j.getValue();
            }
            System.out.println(actual);
        }
        System.out.println("Resposta es = " + actual);
        String s = "La paraula del nivell  " + game.getCurrentPlayer().LAST_LEVEL + "  és  :" + actual;
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public void setContext(Context context)
    {
        this.context = context;
    }
    public void res()
    {
    }


    }
