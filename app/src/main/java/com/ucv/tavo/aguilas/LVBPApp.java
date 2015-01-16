package com.ucv.tavo.aguilas;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by gustavo on 9/12/14.
 */
public class LVBPApp extends Application {

    private static LVBPApp instance;
    private static final String TAG = LVBPApp.class
            .getSimpleName();
    private RequestQueue mRequestQueue;
    private Activity currentActivity;
    private boolean activityVisible;

    public LVBPApp() {
        super();
        instance = this;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public static LVBPApp getInstance() {
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity activity) {
        this.currentActivity = activity;
    }

    public void activityResumed() {
        activityVisible = true;
    }

    public void activityPaused() {
        activityVisible = false;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
