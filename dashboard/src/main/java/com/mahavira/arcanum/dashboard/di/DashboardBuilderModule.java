package com.mahavira.arcanum.dashboard.di;

import android.arch.lifecycle.ViewModel;
import com.mahavira.arcanum.dashboard.presentation.DashboardActivity;
import com.mahavira.arcanum.dashboard.presentation.DashboardViewModel;
import com.mahavira.base.di.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by norman on 23/08/18.
 *
 */

@Module
public abstract class DashboardBuilderModule {

    @ContributesAndroidInjector
    abstract DashboardActivity bindDashboardActivity();

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel.class)
    abstract ViewModel bindDashboardViewModel(DashboardViewModel dashboardViewModel);
}
