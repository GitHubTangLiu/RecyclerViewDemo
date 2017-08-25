package com.example.recyclerviewtest;

import android.app.Application;

/**
 *
 * 类用途：自定义全局异常的application类。用来初始化
 */

public class CrashApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
