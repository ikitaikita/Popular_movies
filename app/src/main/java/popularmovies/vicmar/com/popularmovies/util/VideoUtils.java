package popularmovies.vicmar.com.popularmovies.util;


import java.util.List;

import popularmovies.vicmar.com.popularmovies.Constants;
import popularmovies.vicmar.com.popularmovies.data.models.Video;

/**
 * Created by vik on 04/11/2017.
 */

public class VideoUtils {

    public static Video getTrailerFromVideos(List<Video> videos) {
        Video resultvideo = null;

        for (Video video : videos)
            if (video.getType().contains(Constants.TYPE_TRAILER))
                resultvideo =  video;
     return resultvideo;
    }
}
