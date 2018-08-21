package com.mahavira.arcanum.login.domain.usecase;

import com.mahavira.arcanum.login.domain.entity.UserWithPass;
import com.mahavira.arcanum.login.domain.repo.LoginRepository;
import com.mahavira.base.core.CompletableUseCase;
import io.reactivex.Completable;
import javax.inject.Inject;

/**
 * Created by norman on 20/08/18.
 *
 * Use case for register new user into Firestore database and Firebase Authentication
 */

public class RegisterUseCase implements CompletableUseCase<UserWithPass> {

    private LoginRepository mRepository;

    @Inject
    public RegisterUseCase(LoginRepository repository) {
        mRepository = repository;
    }

    @Override
    public Completable execute(final UserWithPass param) throws Exception {
        return mRepository.register(param.getUser(), param.getPass());
    }
}
