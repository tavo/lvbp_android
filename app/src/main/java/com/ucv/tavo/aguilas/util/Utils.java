package com.ucv.tavo.aguilas.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by gustavo on 12/12/14.
 */
public class Utils {

    public static boolean isOnline(Context context) {
        Context c = context.getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if (ni != null && ni.isConnected())
            return true;
        else
            return false;
    }
}
