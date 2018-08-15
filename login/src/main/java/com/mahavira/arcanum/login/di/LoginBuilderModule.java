package com.mahavira.arcanum.login.di;

import android.arch.lifecycle.ViewModel;

import com.mahavira.arcanum.login.presentation.feature.LoginActivity;
import com.mahavira.arcanum.login.presentation.feature.LoginViewModel;
import com.mahavira.base.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by norman on 13/07/18.
 *
 */

@Module
public abstract class LoginBuilderModule {

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);
}
