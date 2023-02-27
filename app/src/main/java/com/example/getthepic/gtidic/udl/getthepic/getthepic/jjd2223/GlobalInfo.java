package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223;

public class GlobalInfo {
    private final int SPLASH_SCREEN_TIMEOUT = 4000;

    private static int LAST_LEVEL = 0;

    public void UpdateLastLevel(int n)
    {
        LAST_LEVEL = n;
    }

    private static GlobalInfo instance = new GlobalInfo();

    public static GlobalInfo getInstance()
    {
        return instance;
    }

    public int getSPLASH_SCREEN_TIMEOUT() {
        return SPLASH_SCREEN_TIMEOUT;
    }

    public int getLastLevel()
    {
        return LAST_LEVEL;
    }
    public void setLastLevel(int level)
    {
        LAST_LEVEL = level;
    }
}