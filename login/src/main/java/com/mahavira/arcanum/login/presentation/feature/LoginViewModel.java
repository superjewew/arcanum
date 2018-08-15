package com.mahavira.arcanum.login.presentation.feature;

import android.arch.lifecycle.MutableLiveData;
import com.google.firebase.auth.AuthResult;
import com.mahavira.arcanum.login.domain.entity.AuthParam;
import com.mahavira.arcanum.login.domain.usecase.LoginUseCase;
import com.mahavira.base.core.Resource;
import com.mahavira.base.presentation.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

/**
 * Created by norman on 15/08/18.
 */

public class LoginViewModel extends BaseViewModel {

    private final MutableLiveData<Resource<AuthResult>> mLoginResultData = new MutableLiveData<>();

    private LoginUseCase mLoginUseCase;

    @Inject
    public LoginViewModel(LoginUseCase loginUseCase) {
        mLoginUseCase = loginUseCase;
    }

    public MutableLiveData<Resource<AuthResult>> getLoginResultData() {
        return mLoginResultData;
    }

    public void attemptLogin(AuthParam param) {
        mLoginUseCase.execute(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> showLoading())
                .subscribe(this::onLoginSuccess, this::onLoginFailed);

    }

    private void onLoginSuccess(AuthResult result) {
        hideLoading();
        mLoginResultData.setValue(Resource.success(result));
    }

    private void onLoginFailed(Throwable throwable) {
        hideLoading();
        mLoginResultData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }
}
