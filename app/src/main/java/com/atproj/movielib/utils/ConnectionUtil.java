package com.atproj.movielib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class ConnectionUtil {

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            Network[] networks = connectivity.getAllNetworks();
            for (int i = 0; i < networks.length; i++) {
                Network network = networks[i];
                NetworkInfo networkInfo = connectivity.getNetworkInfo(network);
                if (networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
                    return true;
                }
            }
        }

        return false;
    }

}
