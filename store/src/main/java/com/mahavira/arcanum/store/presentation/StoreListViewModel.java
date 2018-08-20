package com.mahavira.arcanum.store.presentation;

import android.arch.lifecycle.MutableLiveData;
import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.arcanum.store.domain.usecase.GetPartnersUseCase;
import com.mahavira.base.core.Resource;
import com.mahavira.base.presentation.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by norman on 16/08/18.
 */

public class StoreListViewModel extends BaseViewModel {

    private final MutableLiveData<Resource<List<Store>>> mStoreData = new MutableLiveData<>();

    private GetPartnersUseCase mGetPartnersUseCase;

    @Inject
    public StoreListViewModel(GetPartnersUseCase getPartnersUseCase) {
        mGetPartnersUseCase = getPartnersUseCase;
    }

    public MutableLiveData<Resource<List<Store>>> getStoreData() {
        return mStoreData;
    }

    void attemptGetPartner() {
        mGetPartnersUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> showLoading())
                .subscribe(this::onSuccessGetStore, this::onFailedGetStore);
    }

    private void onSuccessGetStore(final List<Store> stores) {
        hideLoading();
        mStoreData.setValue(Resource.success(stores));
    }

    private void onFailedGetStore(final Throwable throwable) {
        hideLoading();
        mStoreData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }

}
