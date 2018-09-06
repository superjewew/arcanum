package com.mahavira.arcanum.friends.di;

import android.arch.lifecycle.ViewModel;
import com.mahavira.arcanum.friends.presentation.FriendListFragment;
import com.mahavira.arcanum.friends.presentation.FriendListViewModel;
import com.mahavira.base.di.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by norman on 25/08/18.
 */

@Module
public abstract class FriendBuilderModule {

    @ContributesAndroidInjector
    abstract FriendListFragment bindFriendListFragment();

    @Binds
    @IntoMap
    @ViewModelKey(FriendListViewModel.class)
    abstract ViewModel bindsFriendListViewModel(FriendListViewModel friendListViewModel);
}
