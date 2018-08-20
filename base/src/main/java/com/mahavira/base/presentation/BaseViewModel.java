package com.mahavira.base.presentation;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    private final ObservableBoolean mShowLoading = new ObservableBoolean();

    protected final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

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
