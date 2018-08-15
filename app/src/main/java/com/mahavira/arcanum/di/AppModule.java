package com.mahavira.arcanum.di;

import android.app.Application;
import android.content.Context;


import com.mahavira.arcanum.login.presentation.LoginRouter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 09/07/18.
 *
 */

@Module
class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    LoginRouter provideLoginRouter() {
        return null;
    }

}
