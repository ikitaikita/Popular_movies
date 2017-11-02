package popularmovies.vicmar.com.popularmovies.ui.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import popularmovies.vicmar.com.popularmovies.Constants;
import popularmovies.vicmar.com.popularmovies.R;
import popularmovies.vicmar.com.popularmovies.data.models.Movie;
import popularmovies.vicmar.com.popularmovies.ui.base.BaseRecyclerViewAdapter;

/**
 * Created by vik on 02/11/2017.
 */

public class MoviesAdapter extends BaseRecyclerViewAdapter<MoviesAdapter.MoviesViewHolder> {



    class  MoviesViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title_text) TextView titleText;
        @Bind(R.id.release_time_text) TextView releaseText;
        @Bind(R.id.score_text) TextView scoreText;
        @Bind(R.id.img_photo) ImageView img_photo;


       /* private ImageView img_photo;
        private TextView titleText;
        private TextView releaseText;
        private TextView scoreText;*/


        public MoviesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            //old way
           /* this.img_photo = (ImageView) view.findViewById(R.id.img_photo);
            this.titleText = (TextView) view.findViewById(R.id.title_text);
            this.releaseText = (TextView) view.findViewById(R.id.release_time_text);
            this.scoreText = (TextView) view.findViewById(R.id.score_text);*/


        }
    }
  /*------ nested viewholder ----*/

    private List<Movie> movies;
    private WeakReference<Context> context;

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        this.movies = movies;
        this.context = new WeakReference<>(context);
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_movie, viewGroup, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        MoviesViewHolder vh = (MoviesViewHolder) viewHolder; //safe cast
        Movie movie = movies.get(i);
        vh.titleText.setText(movie.getTitle());
        vh.releaseText.setText(movie.getReleaseDate());
        vh.scoreText.setText(String.valueOf(movie.getVoteAverage()));

        Context contextLocal = context.get();

        Picasso.with(contextLocal)
                .load(Constants.BASE_URL_POSTER + movie.getPosterPath())
                .noFade()
                .into(vh.img_photo);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

  /* Public API*/

    public void replaceData(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public Movie getItem(int position) {
        if (position < 0 || position >= movies.size()) {
            throw new InvalidParameterException("Invalid item index");
        }
        return movies.get(position);
    }

    public void clearData() {
        movies.clear();
        notifyDataSetChanged();
    }
}
