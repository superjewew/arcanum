package com.mahavira.arcanum.login.domain.usecase;

import com.google.firebase.auth.AuthResult;
import com.mahavira.arcanum.login.domain.entity.AuthParam;
import com.mahavira.arcanum.login.domain.repo.LoginRepository;
import com.mahavira.base.core.BaseUseCaseWithParam;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 13/07/18.
 */

public class LoginUseCase implements BaseUseCaseWithParam<AuthParam, AuthResult> {

    LoginRepository mRepo;

    @Inject
    public LoginUseCase(LoginRepository repository) {
        mRepo = repository;
    }

    @Override
    public Single<AuthResult> execute(AuthParam param) {
        return mRepo.login(param.getEmail(), param.getPassword());
    }
}
