package popularmovies.vicmar.com.popularmovies.ui.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import popularmovies.vicmar.com.popularmovies.Constants;
import popularmovies.vicmar.com.popularmovies.R;
import popularmovies.vicmar.com.popularmovies.data.models.Movie;
import popularmovies.vicmar.com.popularmovies.data.models.Video;
import popularmovies.vicmar.com.popularmovies.di.Injector;
import popularmovies.vicmar.com.popularmovies.ui.movies.MoviesAdapter;
import popularmovies.vicmar.com.popularmovies.ui.movies.MoviesContract;
import popularmovies.vicmar.com.popularmovies.ui.movies.MoviesPresenter;
import popularmovies.vicmar.com.popularmovies.util.GenreUtils;

/**
 * Created by vik on 02/11/2017.
 */

public class MovieActivity extends YouTubeBaseActivity implements MovieContract.View, YouTubePlayer.OnInitializedListener{

    public static String EXTRA_MOVIE_ID="EXTRA_MOVIE_ID";



    @Bind (R.id.constraint_layout)ConstraintLayout mRootLayout;
    @Bind(R.id.textview_movie_title) TextView movieTitle;
    @Bind(R.id.textview_movie_genre) TextView movieGenre;
    @Bind(R.id.textview_movie_date) TextView movieDate;
    @Bind(R.id.textview_description) TextView movieDescription;

    @Bind(R.id.imageview_header) ImageView imageHeader;
    @Bind(R.id.imageview_poster) ImageView poster;


    @Bind(R.id.youtube_view)YouTubePlayerView youTubeView;

    // old way
   /*
    private ConstraintLayout mRootLayout;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout refreshLayout;
    private TextView notiText;
    private YouTubePlayerView youTubeView;*/


    private MoviePresenter presenter;
    private Movie movie;
    private ConstraintSet mConstraintSetNormal = new ConstraintSet();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_movie );
        ButterKnife.bind(this);
       /* mRecyclerView = (RecyclerView) findViewById(R.id.movie_recycler);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);
        notiText = (TextView)findViewById(R.id.notiText);*/

        initializeYouTube();
        mConstraintSetNormal.clone(mRootLayout);
        initializePresenter();
        presenter.loadMovie(getIntent().getIntExtra(EXTRA_MOVIE_ID, 1));

    }
    private void initializeYouTube(){
        youTubeView.initialize(Constants.YOUTUBE_API_KEY, this);
    }
    private void initializePresenter() {
        /*DaggerQuestionsComponent.builder()
                .questionsPresenterModule(new QuestionsPresenterModule(this))
                .questionRepositoryComponent(getQuestionRepositoryComponent())
                .build()
                .inject(this);*/

        presenter = new MoviePresenter(this, Injector.provideMovieService()) ;

    }

    private void setupLayout(){

        movieTitle.setText(movie.getTitle());
        movieGenre.setText(GenreUtils.genresFromMovie(movie.getGenres()));

        movieDate.setText(movie.getReleaseDate());
        movieDescription.setText(movie.getOverview());
        Picasso.with(this)
                .load(Constants.BASE_URL_POSTER + movie.getPosterPath())
                .noFade()
                .into(poster);
        Picasso.with(this)
                .load(Constants.BASE_URL_POSTER + movie.getPosterPath())
                .noFade()
                .into(imageHeader);

    }
    @Override
    public void showMovie(Movie movie) {

        this.movie = movie;
        setupLayout();

    }

    @Override
    public void showVideos(List<Video> videos) {

    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText( this, R.string.movie_loading_unsuccessful, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            youTubePlayer.cueVideo("c38r-SAnTWM"); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
            //youTubePlayer.loadVideo("c38r-SAnTWM");
        }
       //youTubePlayer.cueVideo("c38r-SAnTWM");
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Oh no!"+youTubeInitializationResult.toString(),Toast.LENGTH_LONG).show();
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
}
