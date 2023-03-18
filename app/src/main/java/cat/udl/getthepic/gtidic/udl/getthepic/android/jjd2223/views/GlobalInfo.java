package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

public class GlobalInfo {
    private final int SPLASH_SCREEN_TIMEOUT = 4000;

    private static GlobalInfo instance = new GlobalInfo();

    public static GlobalInfo getInstance()
    {
        return instance;
    }

    public int getSPLASH_SCREEN_TIMEOUT() {
        return SPLASH_SCREEN_TIMEOUT;
    }
}