package popularmovies.vicmar.com.popularmovies.data.api;


import popularmovies.vicmar.com.popularmovies.data.models.Movie;
import popularmovies.vicmar.com.popularmovies.data.models.MovieResults;
import popularmovies.vicmar.com.popularmovies.data.models.VideoResults;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Annyce Davis
 */
public interface MovieService
{
    @GET( "discover/movie" )
    Call<MovieResults> getMovieList(@Query("sort_by") String sort_by,
                                    @Query("api_key") String api_key);


    @GET( "movie/{id}" )
    Call<Movie> getMovie (@Path( "id" ) int id,
                          @Query("api_key") String api_key);

    @GET( "movie/{id}/videos" )
    Call<VideoResults> getMovieVideos (@Path( "id" ) Long id,
                                       @Query("api_key") String api_key);
    @GET( "genre/{id}/movies" )
    Call<MovieResults> getMovieListByGenre (@Path( "id" ) Long id,
                                       @Query("api_key") String api_key);



}
