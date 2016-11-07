package com.cloudgy.check;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.cloudgy.check.config.ServerConfig;

/**
 * Created by panyi on 2016/11/7.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }

    private void initApp() {
        context = this;
        //ServerConfig.environment = ServerConfig.Environment.TEST;
        Log.i("env", ServerConfig.environment.toString());
    }

    public static Context getInstance() {
        return context;
    }
}//end class
