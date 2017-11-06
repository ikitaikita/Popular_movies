package popularmovies.vicmar.com.popularmovies.ui.movie;

import popularmovies.vicmar.com.popularmovies.Constants;
import popularmovies.vicmar.com.popularmovies.data.api.MovieService;
import popularmovies.vicmar.com.popularmovies.data.models.Movie;
import popularmovies.vicmar.com.popularmovies.data.models.MovieResults;
import popularmovies.vicmar.com.popularmovies.data.models.Video;
import popularmovies.vicmar.com.popularmovies.data.models.VideoResults;
import popularmovies.vicmar.com.popularmovies.ui.movies.MoviesContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by vik on 02/11/2017.
 */

public class MoviePresenter implements MovieContract.Presenter {
    private final MovieContract.View view;
    private final MovieService service;

    public MoviePresenter (MovieContract.View view, MovieService service)
    {
        this.view = view;
        this.service = service;
    }

    @Override
    public void loadMovie(int id) {
        service.getMovie(id, Constants.getApiKey()).enqueue(new Callback<Movie>()
        {
            @Override
            public void onResponse (Call<Movie> call, Response<Movie> response)
            {
                if ( response.isSuccessful() )
                {

                    view.showMovie( response.body() );
                    Timber.i( "Movies data was loaded from API." );
                }
            }

            @Override
            public void onFailure (Call<Movie> call, Throwable t)
            {
                view.showErrorMessage("Unable to load the movies data from API.");
                Timber.e( t, "Unable to load the books data from API." );
            }
        } );

    }

    @Override
    public void loadVideosFromMovie(long id) {
        service.getMovieVideos(id, Constants.getApiKey()).enqueue(new Callback<VideoResults>()
        {
            @Override
            public void onResponse (Call<VideoResults> call, Response<VideoResults> response)
            {
                if ( response.isSuccessful() )
                {

                    view.showVideo( response.body().getVideoResults() );
                    Timber.i( "Movies data was loaded from API." );
                }
            }

            @Override
            public void onFailure (Call<VideoResults> call, Throwable t)
            {
                view.showErrorMessage("Unable to load the movies data from API.");
                Timber.e( t, "Unable to load the books data from API." );
            }
        } );

    }
}
