package com.mahavira.arcanum.product.presentation;

import android.arch.lifecycle.MutableLiveData;
import com.mahavira.arcanum.product.domain.entity.Boardgame;
import com.mahavira.arcanum.product.domain.usecase.GetProductByNameUseCase;
import com.mahavira.base.core.Resource;
import com.mahavira.base.presentation.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

/**
 * Created by norman on 24/08/18.
 *
 */

public class ProductDetailViewModel extends BaseViewModel {

    private final MutableLiveData<Resource<Boardgame>> mBoardgameData = new MutableLiveData<>();

    private GetProductByNameUseCase mGetProductByNameUseCase;

    @Inject
    ProductDetailViewModel(GetProductByNameUseCase getProductByNameUseCase) {
        mGetProductByNameUseCase = getProductByNameUseCase;
    }

    MutableLiveData<Resource<Boardgame>> getBoardgameData() {
        return mBoardgameData;
    }

    void attemptGetProductByName(String name) {
        mDisposable.add(mGetProductByNameUseCase.execute(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(__ -> doOnSubscribe())
            .subscribe(this::onGetProductSuccess, this::onGetProductFailed));
    }

    private void onGetProductSuccess(final Boardgame boardgame) {
        hideLoading();
        mBoardgameData.setValue(Resource.success(boardgame));
    }

    private void onGetProductFailed(final Throwable throwable) {
        hideLoading();
        mBoardgameData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }
}
