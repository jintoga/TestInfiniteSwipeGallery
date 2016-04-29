package com.dat.testswipegallery;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by DAT on 29-Apr-16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
