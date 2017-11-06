package popularmovies.vicmar.com.popularmovies.ui.movie;

import java.util.List;

import popularmovies.vicmar.com.popularmovies.data.models.Movie;
import popularmovies.vicmar.com.popularmovies.data.models.Video;

/**
 * Created by vik on 02/11/2017.
 */

public class MovieContract {

    interface View {


        void showMovie(Movie movie);

        void showVideo (List<Video> videos);

        void showErrorMessage(String error);



    }

    interface Presenter  {
        void loadMovie(int id);

        void loadVideosFromMovie(long id);



    }
}
