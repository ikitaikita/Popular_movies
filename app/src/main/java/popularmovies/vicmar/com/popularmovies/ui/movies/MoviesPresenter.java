package popularmovies.vicmar.com.popularmovies.ui.movies;

import java.util.List;

import popularmovies.vicmar.com.popularmovies.Constants;
import popularmovies.vicmar.com.popularmovies.data.api.MovieService;
import popularmovies.vicmar.com.popularmovies.data.models.Movie;
import popularmovies.vicmar.com.popularmovies.data.models.MovieResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by vik on 01/11/2017.
 */

public class MoviesPresenter implements MoviesContract.Presenter{

    private final MoviesContract.View view;
    private final MovieService service;

    public MoviesPresenter (MoviesContract.View view, MovieService service)
    {
        this.view = view;
        this.service = service;
    }

    @Override
    public void loadMovies() {
        view.clearMovies();
        service.getMovieList("popularity.desc", Constants.getApiKey()).enqueue(new Callback<MovieResults>()
        {
            @Override
            public void onResponse (Call<MovieResults> call, Response<MovieResults> response)
            {
                if ( response.isSuccessful() )
                {
                    view.stopLoadingIndicator();
                    view.showMovies( response.body().getResults() );
                    Timber.i( "Movies data was loaded from API." );
                }
            }

            @Override
            public void onFailure (Call<MovieResults> call, Throwable t)
            {
                view.showErrorMessage("Unable to load the movies data from API.");
                Timber.e( t, "Unable to load the books data from API." );
            }
        } );
    }

    @Override
    public void loadGenreMovies(long id) {
        view.clearMovies();
        service.getMovieListByGenre(id, Constants.getApiKey()).enqueue(new Callback<MovieResults>()
        {
            @Override
            public void onResponse (Call<MovieResults> call, Response<MovieResults> response)
            {
                if ( response.isSuccessful() )
                {
                    view.showMovies( response.body().getResults() );
                    Timber.i( "Movies data was loaded from API." );
                }
            }

            @Override
            public void onFailure (Call<MovieResults> call, Throwable t)
            {
                view.showErrorMessage("Unable to load the movies data from API.");
                Timber.e( t, "Unable to load the books data from API." );
            }
        } );
    }


    @Override
    public void getMovie(int movieId) {
        view.showMovieDetail(movieId);


    }

    @Override
    public void search(String questionTitle) {

    }
}
