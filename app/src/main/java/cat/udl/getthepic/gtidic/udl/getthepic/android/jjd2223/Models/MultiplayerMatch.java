package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models;

public class MultiplayerMatch {
    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getMatchKey() {
        return matchKey;
    }

    String userName;
    String userEmail;
    String matchKey;

    public MultiplayerMatch(String matchKey, String userName, String userEmail){
        this.matchKey = matchKey;
        this.userName = userName;
        this.userEmail = userEmail;
    }
}