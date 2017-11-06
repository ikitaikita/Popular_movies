package popularmovies.vicmar.com.popularmovies.util;

import java.util.List;

import popularmovies.vicmar.com.popularmovies.Constants;
import popularmovies.vicmar.com.popularmovies.data.models.Genre;

/**
 * Created by vik on 02/11/2017.
 */

public class GenreUtils {

    public static String genresIdsFromMovie(List<Integer> genresList){
        StringBuffer genresString = new StringBuffer();
        for(int genreId : genresList ){
            genresString.append(genreFromId(genreId)).append(",");

        }
        return genresString.toString();

    }
    public static String genresFromMovie(List<Genre> genresList){
        StringBuffer genresString = new StringBuffer();
        for(Genre genre : genresList ){
            genresString.append(genre.getName()).append(",");

        }

        //return genresString.toString();
        return  genresString.toString().substring(0, genresString.length()-1);


    }

    private static String genreFromId(int id){
        String result="";
        switch (id){
            case Constants.GENRE_ACTION:
               result =  Constants.GENRE_ACTION_STR;
                break;
            case Constants.GENRE_ADVENTURE:
                result =  Constants.GENRE_ADVENTURE_STR;
                break;
            case Constants.GENRE_ANIMATION:
                result =  Constants.GENRE_ANIMATION_STR;
                break;
            case Constants.GENRE_COMEDY:
                result =  Constants.GENRE_COMEDY_STR;
                break;
            case Constants.GENRE_CRIME:
                result =  Constants.GENRE_CRIME_STR;
                break;
            case Constants.GENRE_DOCUMENTARY:
                result =  Constants.GENRE_DOCUMENTARY_STR;
                break;
            case Constants.GENRE_DRAMA:
                result =  Constants.GENRE_DRAMA_STR;
                break;
            case Constants.GENRE_FAMILY:
                result =  Constants.GENRE_FAMILY_STR;
                break;
            case Constants.GENRE_FANTASY:
                result =  Constants.GENRE_FANTASY_STR;
                break;
            case Constants.GENRE_FICTION:
                result =  Constants.GENRE_FICTION_STR;
                break;
            case Constants.GENRE_HISTORY:
                result =  Constants.GENRE_HISTORY_STR;
                break;
            case Constants.GENRE_HORROR:
                result =  Constants.GENRE_HORROR_STR;
                break;
            case Constants.GENRE_MUSIC:
                result =  Constants.GENRE_MUSIC_STR;
                break;
            case Constants.GENRE_MYSTERY:
                result =  Constants.GENRE_MYSTERY_STR;
                break;
            case Constants.GENRE_ROMANCE:
                result =  Constants.GENRE_ROMANCE_STR;
                break;
            case Constants.GENRE_THRILLER:
                result =  Constants.GENRE_THRILLER_STR;
                break;
            case Constants.GENRE_TV:
                result =  Constants.GENRE_TV_STR;
                break;
            case Constants.GENRE_WAR:
                result =  Constants.GENRE_WAR_STR;
                break;
            case Constants.GENRE_WESTERN:
                result =  Constants.GENRE_WESTERN_STR;
                break;


        }
        return result;
    }

}
