package com.mahavira.arcanum.store.presentation.detail;

import android.arch.lifecycle.MutableLiveData;
import com.mahavira.arcanum.store.domain.entity.SetVisitParam;
import com.mahavira.arcanum.store.domain.usecase.SetVisitUseCase;
import com.mahavira.base.core.Resource;
import com.mahavira.base.core.SingleLiveEvent;
import com.mahavira.base.presentation.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

/**
 * Created by norman on 20/08/18.
 *
 */

public class StoreDetailViewModel extends BaseViewModel {

    private final MutableLiveData<Resource<Boolean>> mSetVisitResult = new MutableLiveData<>();

    private final SingleLiveEvent<Void> mPlayHereClickedEvent = new SingleLiveEvent<>();

    private final SingleLiveEvent<String> mProductClickedEvent = new SingleLiveEvent<>();

    private SetVisitUseCase mSetVisitUseCase;

    @Inject
    public StoreDetailViewModel(SetVisitUseCase useCase) {
        mSetVisitUseCase = useCase;
    }

    public MutableLiveData<Resource<Boolean>> getSetVisitResult() {
        return mSetVisitResult;
    }

    void attemptSetVisit(String user, String store) throws Exception {
        SetVisitParam param = new SetVisitParam(user, store);
        mDisposable.add(mSetVisitUseCase.execute(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> doOnSubscribe())
                .doFinally(this::hideLoading)
                .subscribe(this::onSetVisitSuccess, this::onSetVisitFailed));
    }

    private void onSetVisitSuccess() {
        mSetVisitResult.setValue(Resource.success(true));
    }

    private void onSetVisitFailed(final Throwable throwable) {
        mSetVisitResult.setValue(Resource.error(null, throwable.getLocalizedMessage(), false));
    }

    SingleLiveEvent<Void> getPlayHereClickedEvent() {
        return mPlayHereClickedEvent;
    }

    SingleLiveEvent<String> getProductClickedEvent() {
        return mProductClickedEvent;
    }

    public void playHere() {
        mPlayHereClickedEvent.call();
    }

    void productClicked(String name) {
        mProductClickedEvent.setValue(name);
    }

}
