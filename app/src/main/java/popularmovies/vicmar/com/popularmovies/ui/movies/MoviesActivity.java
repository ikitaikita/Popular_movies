package popularmovies.vicmar.com.popularmovies.ui.movies;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import popularmovies.vicmar.com.popularmovies.Constants;
import popularmovies.vicmar.com.popularmovies.R;
import popularmovies.vicmar.com.popularmovies.data.models.Movie;
import popularmovies.vicmar.com.popularmovies.di.Injector;
import popularmovies.vicmar.com.popularmovies.ui.base.RecyclerViewListener;

public class MoviesActivity extends AppCompatActivity implements MoviesContract.View{

    @Bind(R.id.movie_recycler) RecyclerView mRecyclerView;
    @Bind(R.id.refresh) SwipeRefreshLayout refreshLayout;
    @Bind(R.id.notiText) TextView notiText;

    // old way
   /* private RecyclerView mRecyclerView;
    private SwipeRefreshLayout refreshLayout;
    private TextView notiText;*/

    private MoviesAdapter adapter;
    private MoviesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       /* mRecyclerView = (RecyclerView) findViewById(R.id.movie_recycler);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);
        notiText = (TextView)findViewById(R.id.notiText);*/

        initializePresenter();
        setupWidgets();
    }

    private void initializePresenter() {
        /*DaggerQuestionsComponent.builder()
                .questionsPresenterModule(new QuestionsPresenterModule(this))
                .questionRepositoryComponent(getQuestionRepositoryComponent())
                .build()
                .inject(this);*/

        presenter = new MoviesPresenter(this, Injector.provideMovieService()) ;
    }

    private void setupWidgets() {
        // Setup recycler view
        adapter = new MoviesAdapter(this,new ArrayList<Movie>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(new RecyclerViewListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                presenter.getMovie(adapter.getItem(position).getId());
            }
        });


        // Refresh layout
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadMovies();
            }
        });

        // Set notification text visible first
        notiText.setVisibility(View.GONE);
    }

    @Override
    protected void onResume ()
    {
        super.onResume();
        presenter.loadMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.genre_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //You can sort the result by passing sorting parameter to the API URL. (see Line 96 of this class and CarmudiAPI class)
        switch(item.getItemId()) {
            case (R.id.genre_all): {
                presenter.loadMovies();
                break;
            }
            case (R.id.genre_action): {
                presenter.loadGenreMovies(Constants.GENRE_ACTION);
                break;
            }
            case (R.id.genre_adventure): {
                presenter.loadGenreMovies(Constants.GENRE_ADVENTURE);
                break;
            }
            case (R.id.genre_animation): {
                presenter.loadGenreMovies(Constants.GENRE_ANIMATION);
                break;
            }
            case (R.id.genre_comedy): {
                presenter.loadGenreMovies(Constants.GENRE_COMEDY);

                break;
            }
            case (R.id.genre_crime): {
                presenter.loadGenreMovies(Constants.GENRE_CRIME);
                break;
            }
            case (R.id.genre_documentary): {
                presenter.loadGenreMovies(Constants.GENRE_DOCUMENTARY);
                break;
            }
            case (R.id.genre_drama): {
                presenter.loadGenreMovies(Constants.GENRE_DRAMA);
                break;
            }
            case (R.id.genre_family): {
                presenter.loadGenreMovies(Constants.GENRE_FAMILY);
                break;
            }
            case (R.id.genre_fantasy): {
                presenter.loadGenreMovies(Constants.GENRE_FANTASY);
                break;
            }
            case (R.id.genre_fiction): {
                presenter.loadGenreMovies(Constants.GENRE_FICTION);
                break;
            }
            case (R.id.genre_history): {
                presenter.loadGenreMovies(Constants.GENRE_HISTORY);
                break;
            }
            case (R.id.genre_horror): {
                presenter.loadGenreMovies(Constants.GENRE_HORROR);
                break;
            }
            case (R.id.genre_music): {
                presenter.loadGenreMovies(Constants.GENRE_MUSIC);
                break;
            }
            case (R.id.genre_romance): {
                presenter.loadGenreMovies(Constants.GENRE_ROMANCE);
                break;
            }
            case (R.id.genre_thriller): {
                presenter.loadGenreMovies(Constants.GENRE_THRILLER);
                break;
            }
            case (R.id.genre_tv): {
                presenter.loadGenreMovies(Constants.GENRE_TV);
                break;
            }
            case (R.id.genre_war): {
                presenter.loadGenreMovies(Constants.GENRE_WAR);
                break;
            }
            case (R.id.genre_western): {
                presenter.loadGenreMovies(Constants.GENRE_WESTERN);
                break;
            }
            case (R.id.genre_mystery): {
                presenter.loadGenreMovies(Constants.GENRE_MYSTERY);
                break;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clearMovies() {
        adapter.clearData();
    }

    @Override
    public void showMovies(List<Movie> movies) {

        notiText.setVisibility(View.GONE);
        adapter.replaceData(movies);

    }

    @Override
    public void showNoDataMessage() {
        showNotification(getString(R.string.msg_no_data));
    }

    @Override
    public void showErrorMessage(String error) {
        showNotification(error);

    }


    @Override
    public void showMovieDetail(int movieId) {
        /*Intent intent = new Intent( MoviesActivity.this, MovieActivity.class );
        intent.putExtra( MovieActivity.EXTRA_BOOK_ID, movieId );
        startActivity(intent);*/
        Toast.makeText( this,"detail of movie with id " + movieId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void stopLoadingIndicator() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showEmptySearchResult() {
        showNotification(getString(R.string.msg_empty_search_result));
    }

    private void showNotification(String message){
        notiText.setVisibility(View.VISIBLE);
        notiText.setText(message);
    }
}
