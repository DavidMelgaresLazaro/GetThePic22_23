package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models;

import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class levels {

    public static String[] levels = {"COTXE","CAMISA","ESCOLA"};
    public static int[] imagesresources = {R.drawable.nissan ,};



    public static String GetLevel(int level)
    {
        return levels[level];
    }
    public static int Getimage(int level) {return imagesresources[level];}
}
