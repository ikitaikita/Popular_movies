package popularmovies.vicmar.com.popularmovies.ui.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
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
import popularmovies.vicmar.com.popularmovies.util.VideoUtils;

/**
 * Created by vik on 02/11/2017.
 */

public class MovieActivity extends YouTubeBaseActivity implements MovieContract.View, YouTubePlayer.OnInitializedListener{

    public static String EXTRA_MOVIE_ID="EXTRA_MOVIE_ID";
    private static final int RECOVERY_REQUEST = 1;



    @Bind (R.id.constraint_layout)ConstraintLayout mRootLayout;
    @Bind(R.id.textview_movie_title) TextView movieTitle;
    @Bind(R.id.textview_movie_genre) TextView movieGenre;
    @Bind(R.id.textview_movie_date) TextView movieDate;
    @Bind(R.id.textview_description) TextView movieDescription;

   // @Bind(R.id.imageview_header) ImageView imageHeader;
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
    private String keyvideo;
    private ConstraintSet mConstraintSetNormal = new ConstraintSet();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_movie );
        ButterKnife.bind(this);
       /* mRecyclerView = (RecyclerView) findViewById(R.id.movie_recycler);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);
        notiText = (TextView)findViewById(R.id.notiText);*/


        mConstraintSetNormal.clone(mRootLayout);
        initializePresenter();
        presenter.loadMovie(getIntent().getIntExtra(EXTRA_MOVIE_ID, 1));
        presenter.loadVideosFromMovie(getIntent().getIntExtra(EXTRA_MOVIE_ID, 1));

    }

    @Override
    public void startActivityForResult(@RequiresPermission Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }


    private void initializeYouTube(String keyvideo){
        this.keyvideo = keyvideo;
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
        /*Picasso.with(this)
                .load(Constants.BASE_URL_POSTER + movie.getPosterPath())
                .noFade()
                .into(imageHeader);*/

    }
    @Override
    public void showMovie(Movie movie) {

        this.movie = movie;
        setupLayout();

    }

    @Override
    public void showVideo(List<Video> videos) {
        Video video = VideoUtils.getTrailerFromVideos(videos);
        initializeYouTube(video.getKey());

    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText( this, R.string.movie_loading_unsuccessful, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            youTubePlayer.cueVideo(keyvideo); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
           //youTubePlayer.loadVideo(keyvideo);

        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = getString(R.string.player_error)+ youTubeInitializationResult.toString();
            showErrorMessage(error);
            //Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }

    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECOVERY_REQUEST) {

            getYouTubePlayerProvider().initialize(Constants.YOUTUBE_API_KEY, this);
        }
    }
}
