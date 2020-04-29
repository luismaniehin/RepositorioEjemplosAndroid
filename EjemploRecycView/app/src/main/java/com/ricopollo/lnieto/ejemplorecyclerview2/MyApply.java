package com.ricopollo.lnieto.ejemplorecyclerview2;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MyApply extends Application {
    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context){
        MyApply apply = (MyApply) context.getApplicationContext();
        return  apply.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }
}
