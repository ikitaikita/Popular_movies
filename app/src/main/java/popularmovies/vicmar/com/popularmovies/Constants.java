package popularmovies.vicmar.com.popularmovies;

/**
 * Created by vik on 31/10/2017.
 */

public class Constants {

    public static String getApiKey() {
        return API_KEY;
    }

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    private static String API_KEY = "9a843c387fae60e9817d91f3e30cfdfb";

    public static String BASE_URL_MOVIES = "http://api.themoviedb.org/3/";
    public static String BASE_URL_POSTER = "http://image.tmdb.org/t/p/w500";

    // GENRES

    public static long GENRE_ACTION = 28;
    public static long GENRE_ADVENTURE = 12;
    public static long GENRE_ANIMATION = 16;
    public static long GENRE_COMEDY = 35;
    public static long GENRE_CRIME = 80;
    public static long GENRE_DOCUMENTARY = 99;
    public static long GENRE_DRAMA = 18;
    public static long GENRE_FAMILY = 10751;
    public static long GENRE_FANTASY = 14;
    public static long GENRE_HISTORY = 36;
    public static long GENRE_HORROR = 27;
    public static long GENRE_MUSIC = 10402;
    public static long GENRE_MYSTERY = 9648;
    public static long GENRE_ROMANCE = 10749;
    public static long GENRE_FICTION = 878;
    public static long GENRE_TV = 10770;
    public static long GENRE_THRILLER = 53;
    public static long GENRE_WAR = 10752;
    public static long GENRE_WESTERN = 37;


}
