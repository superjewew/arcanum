package com.mahavira.arcanum.login.domain.usecase;

import com.mahavira.arcanum.login.domain.entity.User;
import com.mahavira.arcanum.login.domain.repo.LoginRepository;
import com.mahavira.base.core.CompletableUseCase;
import io.reactivex.Completable;
import javax.inject.Inject;

/**
 * Created by norman on 20/08/18.
 */

public class RegisterUseCase implements CompletableUseCase<User> {

    private LoginRepository mRepository;

    @Inject
    public RegisterUseCase(LoginRepository repository) {
        mRepository = repository;
    }

    @Override
    public Completable execute(final User param) throws Exception {
        return mRepository.register(param);
    }
}
