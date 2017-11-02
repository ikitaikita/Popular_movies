package popularmovies.vicmar.com.popularmovies.ui.movies;

import java.util.List;

import popularmovies.vicmar.com.popularmovies.data.models.Movie;

/**
 * Created by vik on 01/11/2017.
 */

public interface MoviesContract {

    interface View {
        void clearMovies();

        void showMovies(List<Movie> movies);

        void showNoDataMessage();

        void showErrorMessage(String error);

        void showMovieDetail(int movieId);

        void stopLoadingIndicator();

        void showEmptySearchResult();
    }

    interface Presenter  {
        void loadMovies();

        void getMovie(int movieId);
        void loadGenreMovies(long genreId);


        void search(String questionTitle);
    }
}
