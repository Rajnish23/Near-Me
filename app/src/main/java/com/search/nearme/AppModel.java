package com.search.nearme;

import android.app.Application;

import com.search.nearme.utils.APIService;
import com.search.nearme.utils.AppConst;

public class AppModel extends Application {


    private APIService apiService;

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
