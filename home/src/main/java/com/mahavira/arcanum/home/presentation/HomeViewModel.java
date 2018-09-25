package com.mahavira.arcanum.home.presentation;

import android.arch.lifecycle.MutableLiveData;
import com.mahavira.arcanum.home.domain.usecase.GetRecentStoreUseCase;
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

    private final MutableLiveData<Resource<List<String>>> mRecentStoreData = new MutableLiveData<>();

    private final MutableLiveData<String> mItemClicked = new MutableLiveData<>();

    private GetRecentStoreUseCase mGetRecentStoreUseCase;

    @Inject
    HomeViewModel(GetRecentStoreUseCase getRecentStoreUseCase) {
        mGetRecentStoreUseCase = getRecentStoreUseCase;
    }

    MutableLiveData<Resource<List<String>>> getRecentStoreData() {
        return mRecentStoreData;
    }

    MutableLiveData<String> getItemClicked() {
        return mItemClicked;
    }

    void onItemClicked(final String item) {
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

    private void onGetRecentSuccess(final List<String> strings) {
        mRecentStoreData.setValue(Resource.success(strings));
    }

    private void onGetRecentFailed(final Throwable throwable) {
        mRecentStoreData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }

}
