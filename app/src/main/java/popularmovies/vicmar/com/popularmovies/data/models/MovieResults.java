package popularmovies.vicmar.com.popularmovies.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vik on 31/10/2017.
 */

public class MovieResults {

    @SerializedName("results")
    @Expose
    private List<Movie> results = null;



    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

}
