package popularmovies.vicmar.com.popularmovies.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vik on 31/10/2017.
 */

public class VideoResults {


    @SerializedName("results")
    @Expose
    private List<Video> videoResults = null;

    public List<Video> getVideoResults() {
        return videoResults;
    }

    public void setVideoResults(List<Video> videoResults) {
        this.videoResults = videoResults;
    }






}
