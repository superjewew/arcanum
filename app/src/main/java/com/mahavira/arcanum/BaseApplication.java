package com.mahavira.arcanum;

import android.app.Activity;
import android.app.Application;

import com.mahavira.arcanum.di.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by norman on 09/07/18.
 */

public class BaseApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
    }
}
