package com.mahavira.arcanum.login.presentation.feature.register;

import android.arch.lifecycle.MutableLiveData;
import com.mahavira.arcanum.login.domain.entity.User;
import com.mahavira.arcanum.login.domain.usecase.RegisterUseCase;
import com.mahavira.base.core.Resource;
import com.mahavira.base.presentation.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

/**
 * Created by norman on 20/08/18.
 *
 */

public class RegisterViewModel extends BaseViewModel {

    private final MutableLiveData<Resource<Boolean>> mRegisterResultData = new MutableLiveData<>();

    private RegisterUseCase mRegisterUseCase;

    @Inject
    public RegisterViewModel(RegisterUseCase registerUseCase) {
        mRegisterUseCase = registerUseCase;
    }

    MutableLiveData<Resource<Boolean>> getRegisterResultData() {
        return mRegisterResultData;
    }

    public void attemptRegister(User user) {
        try {
            mDisposable.add(mRegisterUseCase.execute(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(__ -> doOnSubscribe())
                    .subscribe(this::onRegisterSuccess, this::onRegisterFailed));
        } catch (Exception e) {
            e.printStackTrace();
            mRegisterResultData.setValue(Resource.error(null, e.getLocalizedMessage(), false));
        }
    }

    private void onRegisterSuccess() {
        hideLoading();
        mRegisterResultData.setValue(Resource.success(true));
    }

    private void onRegisterFailed(final Throwable throwable) {
        hideLoading();
        mRegisterResultData.setValue(Resource.error(null, throwable.getLocalizedMessage(), false));
    }

}
