package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class levels {
    private static int randomIndex = -2;
    public static String[] levelsP = {"COTXE","CAMISA","ESCOLA","LLIBRE","CADIRA","COIXI","EDIFICI","FABRICA","FAROLA","MOBIL","PANTALLA","RAMELL","PARET","BALCO","PESES","ESCOMBRA","RODES","GUITARRA"};
    public static Integer[] imagesresources = {R.drawable.nissan,R.drawable.camiseta,R.drawable.escola,R.drawable.llibre,R.drawable.cadira,R.drawable.coixi,R.drawable.edifici,R.drawable.fabrica,R.drawable.farola,R.drawable.iphone,R.drawable.pantalla,R.drawable.ramell,R.drawable.paret,R.drawable.balco,R.drawable.peses,R.drawable.escombra,R.drawable.rodes,R.drawable.guitarra};

    private static Map<Character, Integer> CardDictionary = new HashMap<Character, Integer>() {{
        put('a', R.drawable.a);
        put('b', R.drawable.b);
        put('c', R.drawable.c);
        put('d', R.drawable.d);
        put('e', R.drawable.e);
        put('f', R.drawable.f);
        put('g', R.drawable.g);
        put('h', R.drawable.h);
        put('i', R.drawable.i);
        put('j', R.drawable.j);
        put('k', R.drawable.k);
        put('l', R.drawable.l);
        put('m', R.drawable.m);
        put('n', R.drawable.n);
        put('ñ', R.drawable.enye);
        put('o', R.drawable.o);
        put('p', R.drawable.p);
        put('q', R.drawable.q);
        put('r', R.drawable.r);
        put('s', R.drawable.s);
        put('t', R.drawable.t);
        put('u', R.drawable.u);
        put('v', R.drawable.v);
        put('w', R.drawable.w);
        put('x', R.drawable.x);
        put('y', R.drawable.y);
        put('z', R.drawable.z);
    }};




    public static String GetLevel(int level)
    {
        return levelsP[level];
    }
    public static Integer Getimage(int level) {return imagesresources[level];}

    public static String GetRandomLevelStr() {
        int randomIndex = GetRandomLevel();
        return levelsP[randomIndex];
    }

    public static int GetRandomLevel() {
        if (randomIndex == -2) {
            Random rand = new Random();
            randomIndex = rand.nextInt(levelsP.length);
        }
        return randomIndex;
    }


    public static void onLevelPassed() {
        randomIndex = -2;

    }

    public static Integer getCardDrawable(char c)
    {
        return CardDictionary.get(Character.toLowerCase(c));
    }
}










