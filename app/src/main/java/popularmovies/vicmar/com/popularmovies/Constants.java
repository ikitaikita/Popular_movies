package popularmovies.vicmar.com.popularmovies;

/**
 * Created by vik on 31/10/2017.
 */

public class Constants {



    private static String API_KEY = "9a843c387fae60e9817d91f3e30cfdfb";
    public static String YOUTUBE_API_KEY= "AIzaSyDghiHgc_V3SRFbrdMhAjr4Gh3MZcwr0PE";

    public static String BASE_URL_MOVIES = "http://api.themoviedb.org/3/";
    public static String BASE_URL_POSTER = "http://image.tmdb.org/t/p/w500";

    // GENRES

    public static final int GENRE_ACTION = 28;
    public static final int GENRE_ADVENTURE = 12;
    public static final int GENRE_ANIMATION = 16;
    public static final int GENRE_COMEDY = 35;
    public static final int GENRE_CRIME = 80;
    public static final int GENRE_DOCUMENTARY = 99;
    public static final int GENRE_DRAMA = 18;
    public static final int GENRE_FAMILY = 10751;
    public static final int GENRE_FANTASY = 14;
    public static final int GENRE_HISTORY = 36;
    public static final int GENRE_HORROR = 27;
    public static final int GENRE_MUSIC = 10402;
    public static final int GENRE_MYSTERY = 9648;
    public static final int GENRE_ROMANCE = 10749;
    public static final int GENRE_FICTION = 878;
    public static final int GENRE_TV = 10770;
    public static final int GENRE_THRILLER = 53;
    public static final int GENRE_WAR = 10752;
    public static final int GENRE_WESTERN = 37;


    public static String GENRE_ACTION_STR = "Action";
    public static String GENRE_ADVENTURE_STR = "Adventure";
    public static String GENRE_ANIMATION_STR= "Animation";
    public static String GENRE_COMEDY_STR = "Comedy";
    public static String GENRE_CRIME_STR = "Crime";
    public static String GENRE_DOCUMENTARY_STR = "Documentary";
    public static String GENRE_DRAMA_STR = "Drama";
    public static String GENRE_FAMILY_STR = "Family";
    public static String GENRE_FANTASY_STR = "Fantasy";
    public static String GENRE_HISTORY_STR = "History";
    public static String GENRE_HORROR_STR = "Horror";
    public static String GENRE_MUSIC_STR = "Music";
    public static String GENRE_MYSTERY_STR = "Mystery";
    public static String GENRE_ROMANCE_STR = "Romance";
    public static String GENRE_FICTION_STR = "Fiction";
    public static String GENRE_TV_STR = "Tv";
    public static String GENRE_THRILLER_STR = "Thriller";
    public static String GENRE_WAR_STR = "War";
    public static String GENRE_WESTERN_STR = "Western";

    public static String getApiKey() {
        return API_KEY;
    }

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }


}
