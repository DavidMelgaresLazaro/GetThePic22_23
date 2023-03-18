package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models;

public class levels {

    public static String[] levels = {"COTXE","CAMISA","ESCOLA"};
    public static String[] imagesresources = {"R.drawable.nissan.png"};



    public static String GetLevel(int level)
    {
        return levels[level];
    }
    public static String Getimage(int level) {return imagesresources[level];}
}
