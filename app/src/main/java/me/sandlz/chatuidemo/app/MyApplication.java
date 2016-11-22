package me.sandlz.chatuidemo.app;

import android.app.Application;

import org.xutils.x;

import me.sandlz.chatuidemo.BuildConfig;

/**
 * Created by liuzhu on 2016/11/16.
 * Description :
 * Usage :
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
