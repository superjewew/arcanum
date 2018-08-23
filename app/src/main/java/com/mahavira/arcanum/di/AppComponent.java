package com.mahavira.arcanum.di;

import android.app.Application;
import com.mahavira.arcanum.BaseApplication;
import com.mahavira.arcanum.dashboard.di.DashboardModule;
import com.mahavira.arcanum.home.di.HomeModule;
import com.mahavira.arcanum.login.di.LoginModule;
import com.mahavira.arcanum.store.di.StoreModule;
import com.mahavira.base.di.BaseModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

/**
 * Created by bobbi on 13/03/18.
 *
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        BaseModule.class,
        LoginModule.class,
        StoreModule.class,
        HomeModule.class,
        DashboardModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(BaseApplication application);
}
