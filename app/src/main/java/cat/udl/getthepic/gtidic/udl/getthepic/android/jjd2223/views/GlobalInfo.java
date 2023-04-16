package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import java.util.Date;

public class GlobalInfo {
    private final int SPLASH_SCREEN_TIMEOUT = 4000;

    private Date last_login ;

    private Long last_points;

    private static GlobalInfo instance = new GlobalInfo();

    public static GlobalInfo getInstance()
    {
        return instance;
    }

    public int getSPLASH_SCREEN_TIMEOUT() {
        return SPLASH_SCREEN_TIMEOUT;
    }

    public Date getLastLogin()
    {
        return last_login;
    }
    public void setLast_login(Date date)
    {
        last_login = date;
    }

    public Long getLast_points() {
        return last_points;
    }

    public void setLast_points(Long last_points) {
        this.last_points = last_points;
    }
}