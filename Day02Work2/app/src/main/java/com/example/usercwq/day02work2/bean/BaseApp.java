package com.example.usercwq.day02work2.bean;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by usercwq on 2019/9/22.
 */

public class BaseApp extends Application {
    public static BaseApp sBaseApp;
    public static int widthPixels;
    public  static int heightPixels;

    @Override
    public void onCreate() {
        super.onCreate();

        sBaseApp = this;
        getWh();
    }

    /**
     *
     * 获取屏幕的宽高
     */
    private void getWh() {
        WindowManager systemService = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = systemService.getDefaultDisplay();
        DisplayMetrics metrics=new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);

        widthPixels = metrics.widthPixels;
        heightPixels = metrics.heightPixels;
    }

    public static Resources getRes(){
        return sBaseApp.getResources();
    }
}
