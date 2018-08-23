package com.mahavira.arcanum.login.domain.repo;


import com.google.firebase.auth.AuthResult;

import com.mahavira.base.entity.User;
import io.reactivex.Completable;
import io.reactivex.Single;


/**
 * Created by norman on 13/07/18.
 *
 */

public interface LoginRepository {
    Single<AuthResult> login(String email, String password);
    Completable register(User param, String pass);
}
