package com.mahavira.arcanum.home.presentation;

import android.arch.lifecycle.MutableLiveData;
import com.mahavira.arcanum.home.domain.usecase.GetRecentStoreUseCase;
import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.base.core.Resource;
import com.mahavira.base.presentation.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by norman on 23/08/18.
 *
 */

public class HomeViewModel extends BaseViewModel {

    private final MutableLiveData<Resource<List<Store>>> mRecentStoreData = new MutableLiveData<>();

    private final MutableLiveData<Store> mItemClicked = new MutableLiveData<>();

    private GetRecentStoreUseCase mGetRecentStoreUseCase;

    @Inject
    HomeViewModel(GetRecentStoreUseCase getRecentStoreUseCase) {
        mGetRecentStoreUseCase = getRecentStoreUseCase;
    }

    MutableLiveData<Resource<List<Store>>> getRecentStoreData() {
        return mRecentStoreData;
    }

    MutableLiveData<Store> getItemClicked() {
        return mItemClicked;
    }

    void onItemClicked(final Store item) {
        mItemClicked.setValue(item);
    }

    void attemptGetRecentStore(String email) {
        mDisposable.add(mGetRecentStoreUseCase.execute(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> doOnSubscribe())
                .doFinally(this::hideLoading)
                .subscribe(this::onGetRecentSuccess, this::onGetRecentFailed));
    }

    private void onGetRecentSuccess(final List<Store> stores) {
        mRecentStoreData.setValue(Resource.success(stores));
    }

    private void onGetRecentFailed(final Throwable throwable) {
        mRecentStoreData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }

}
