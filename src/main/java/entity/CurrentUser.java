package entity;

public class CurrentUser {
    private static String accessToken;
    private static String topSongs;

    public static String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static String getTopSongs() {
        return topSongs;
    }

    public void setTopSongs(String topSongs) {
        this.topSongs = topSongs;
    }
}