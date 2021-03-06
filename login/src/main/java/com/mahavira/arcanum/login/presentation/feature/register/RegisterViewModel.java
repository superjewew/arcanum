package com.mahavira.arcanum.login.presentation.feature.register;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import com.mahavira.arcanum.login.domain.entity.Pass;
import com.mahavira.arcanum.login.domain.entity.RegisterValidator;
import com.mahavira.arcanum.login.domain.entity.UserWithPass;
import com.mahavira.arcanum.login.domain.usecase.RegisterUseCase;
import com.mahavira.base.core.Resource;
import com.mahavira.base.entity.User;
import com.mahavira.base.presentation.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

/**
 * Created by norman on 20/08/18.
 *
 */

public class RegisterViewModel extends BaseViewModel {

    public final ObservableField<String> mNameErrorMessage = new ObservableField<>();

    public final ObservableField<String> mEmailErrorMessage = new ObservableField<>();

    public final ObservableField<String> mPasswordErrorMessage = new ObservableField<>();

    public final ObservableField<String> mConfirmErrorMessage = new ObservableField<>();

    private final MutableLiveData<Resource<User>> mRegisterResultData = new MutableLiveData<>();

    private RegisterUseCase mRegisterUseCase;

    @Inject
    public RegisterViewModel(RegisterUseCase registerUseCase) {
        mRegisterUseCase = registerUseCase;
    }

    MutableLiveData<Resource<User>> getRegisterResultData() {
        return mRegisterResultData;
    }

    public void attemptRegister(User user, Pass secret) {
        RegisterValidator validator = new RegisterValidator();
        validator.setInput(user, secret);
        if(validator.isValid()) {
            UserWithPass userWithPass = new UserWithPass(user, secret.getPass());

            try {
                mDisposable.add(mRegisterUseCase.execute(userWithPass)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(__ -> doOnSubscribe())
                        .subscribe(() -> onRegisterSuccess(user), this::onRegisterFailed));
            } catch (Exception e) {
                e.printStackTrace();
                mRegisterResultData.setValue(Resource.error(null, e.getLocalizedMessage(), null));
            }
        }

        mNameErrorMessage.set(validator.mNameError);
        mEmailErrorMessage.set(validator.mEmailError);
        mPasswordErrorMessage.set(validator.mPasswordError);
        mConfirmErrorMessage.set(validator.mConfirmError);
    }

    private void onRegisterSuccess(User user) {
        hideLoading();
        mRegisterResultData.setValue(Resource.success(user));
    }

    private void onRegisterFailed(final Throwable throwable) {
        hideLoading();
        mRegisterResultData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }

}
