package com.mahavira.arcanum.home.di;

import android.arch.lifecycle.ViewModel;
import com.mahavira.arcanum.home.presentation.HomeFragment;
import com.mahavira.arcanum.home.presentation.HomeViewModel;
import com.mahavira.base.di.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by norman on 23/08/18.
 */

@Module
public abstract class HomeBuilderModule {

    @ContributesAndroidInjector
    abstract HomeFragment bindHomeFragment();

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);
}
