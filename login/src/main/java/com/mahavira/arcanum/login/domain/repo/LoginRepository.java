package com.mahavira.arcanum.login.domain.repo;


import com.google.firebase.auth.AuthResult;

import io.reactivex.Single;


/**
 * Created by norman on 13/07/18.
 */

public interface LoginRepository {
    Single<AuthResult> login(String email, String password);
}
