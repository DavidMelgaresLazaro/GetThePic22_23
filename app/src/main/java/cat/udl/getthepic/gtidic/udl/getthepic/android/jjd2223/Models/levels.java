package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models;

import android.graphics.drawable.Drawable;

import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class levels {

    public static String[] levels = {"COTXE","CAMISA","ESCOLA"};
    public static int[] imagesresources = {R.drawable.nissan,R.drawable.camiseta};



    public static String GetLevel(int level)
    {
        return levels[level];
    }
    public static int Getimage(int level) {return imagesresources[level];}
}
