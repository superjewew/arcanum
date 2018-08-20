package com.mahavira.arcanum.store.di;

import android.arch.lifecycle.ViewModel;
import com.mahavira.arcanum.store.presentation.StoreListActivity;
import com.mahavira.arcanum.store.presentation.StoreListFragment;
import com.mahavira.arcanum.store.presentation.StoreListViewModel;
import com.mahavira.base.di.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by norman on 16/08/18.
 */

@Module
public abstract class StoreBuilderModule {

    @ContributesAndroidInjector
    abstract StoreListActivity bindStoreListActivity();

    @ContributesAndroidInjector
    abstract StoreListFragment bindStoreListFragment();

    @Binds
    @IntoMap
    @ViewModelKey(StoreListViewModel.class)
    abstract ViewModel bindStoreListViewModel(StoreListViewModel storeListViewModel);

}
