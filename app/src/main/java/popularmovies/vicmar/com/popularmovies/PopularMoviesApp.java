package popularmovies.vicmar.com.popularmovies;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by vik on 01/11/2017.
 */

public class PopularMoviesApp extends Application {
    private static PopularMoviesApp instance;

    @Override
    public void onCreate()
    {
        super.onCreate();

        instance = this;

       /* if (BuildConfig.DEBUG)
        {
            Timber.plant(new Timber.DebugTree());
        }

        Timber.i("Creating our Application");*/
    }

    public static PopularMoviesApp getInstance ()
    {
        return instance;
    }

    public static boolean hasNetwork ()
    {
        return instance.checkIfHasNetwork();
    }

    public boolean checkIfHasNetwork()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService( Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
