package com.mahavira.arcanum.store.di;

import android.arch.lifecycle.ViewModel;
import com.mahavira.arcanum.store.presentation.StoreListActivity;
import com.mahavira.arcanum.store.presentation.StoreListFragment;
import com.mahavira.arcanum.store.presentation.StoreListViewModel;
import com.mahavira.arcanum.store.presentation.detail.StoreDetailActivity;
import com.mahavira.arcanum.store.presentation.detail.StoreDetailViewModel;
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

    @ContributesAndroidInjector
    abstract StoreDetailActivity bindStoreDetailActivity();

    @Binds
    @IntoMap
    @ViewModelKey(StoreListViewModel.class)
    abstract ViewModel bindStoreListViewModel(StoreListViewModel storeListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(StoreDetailViewModel.class)
    abstract ViewModel bindStoreDetailViewModel(StoreDetailViewModel storeDetailViewModel);

}
