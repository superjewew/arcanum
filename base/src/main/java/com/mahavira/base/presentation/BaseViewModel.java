package com.mahavira.base.presentation;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    public final ObservableBoolean mShowLoading = new ObservableBoolean();

    public final CompositeDisposable mDisposable = new CompositeDisposable();

    protected void doOnSubscribe() {
        showLoading();
    }

    protected void showLoading() {
        mShowLoading.set(true);
    }

    protected void hideLoading() {
        mShowLoading.set(false);
    }

}
