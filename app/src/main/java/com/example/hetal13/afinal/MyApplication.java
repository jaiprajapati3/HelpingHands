package com.example.hetal13.afinal;

/**
 * Created by Lincoln on 14/10/15.
 */

import android.app.Application;

/**
 * Created by Ravi on 13/05/15.
 */


public class MyApplication extends Application {

    public static final String TAG = MyApplication.class
            .getSimpleName();

    //private RequestQueue mRequestQueue;

    private static MyApplication mInstance;

    private PrefManager pref;
   // private Tracker mTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // UNIVERSAL IMAGE LOADER SETUP
       /* DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP*/
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


    public PrefManager getPrefManager() {
        if (pref == null) {
            pref = new PrefManager(this);
        }

        return pref;
    }





}
