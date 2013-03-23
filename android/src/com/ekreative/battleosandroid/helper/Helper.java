package com.ekreative.battleosandroid.helper;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Helper {

	public static boolean isConnectingToInternet(Activity activity){
        ConnectivityManager connectivity = (ConnectivityManager) activity.getSystemService(activity.CONNECTIVITY_SERVICE);
          if (connectivity != null){
              NetworkInfo[] info = connectivity.getAllNetworkInfo();
              if (info != null){
                  for (int i = 0; i < info.length; i++){
                      if (info[i].getState() == NetworkInfo.State.CONNECTED){
                          return true;
                      }
                  }
              }
          }
          return false;
    }
}
