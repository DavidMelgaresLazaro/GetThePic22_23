package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models;

import android.graphics.drawable.Drawable;

import java.util.Random;

import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class levels {
    private static int randomIndex = -2;
    public static String[] levelsP = {"COTXE","CAMISA","ESCOLA"};
    public static Integer[] imagesresources = {R.drawable.nissan,R.drawable.camiseta,R.drawable.escola};



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
}










