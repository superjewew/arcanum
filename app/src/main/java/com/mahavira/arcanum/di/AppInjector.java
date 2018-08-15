package com.mahavira.arcanum.di;

import com.mahavira.arcanum.BaseApplication;

public class AppInjector {

    private AppInjector() {
    }

    public static void init(BaseApplication application) {
        DaggerAppComponent
                .builder()
                .application(application)
                .build()
                .inject(application);

    }
}