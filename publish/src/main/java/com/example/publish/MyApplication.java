package com.example.publish;

import android.app.Application;

import org.xutils.x;

/**
 * Created by syYhm on 2016/8/12.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
