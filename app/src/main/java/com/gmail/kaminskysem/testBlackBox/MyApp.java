package com.gmail.kaminskysem.testBlackBox;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class MyApp extends Application {
    SharedPreferences mPrefs;

    @Override
    public void onCreate() {
        super.onCreate();

        Context mContext = this.getApplicationContext();
        mPrefs = mContext.getSharedPreferences("myAppPrefs", 0);




    }


    public boolean getFirstRun() {
            return mPrefs.getBoolean("firstRun", true);
        }
    public void setRunned() {
        SharedPreferences.Editor edit = mPrefs.edit();
        edit.putBoolean("firstRun", false);
        edit.commit();
    }
}
