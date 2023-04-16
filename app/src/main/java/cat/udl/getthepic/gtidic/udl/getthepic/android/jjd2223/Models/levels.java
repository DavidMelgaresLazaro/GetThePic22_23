package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models;

import android.graphics.drawable.Drawable;

import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class levels {

    public static String[] levels = {"COTXE","CAMISA","ESCOLA"};
    public static Integer[] imagesresources = {R.drawable.nissan,R.drawable.camiseta,R.drawable.escola};



    public static String GetLevel(int level)
    {
        return levels[level];
    }
    public static Integer Getimage(int level) {return imagesresources[level];}
}
