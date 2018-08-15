package com.mahavira.arcanum.login.data;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mahavira.arcanum.login.domain.repo.LoginRepository;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import io.reactivex.Single;

/**
 * Created by norman on 15/08/18.
 */

public class LoginRepoImpl implements LoginRepository {

    FirebaseAuth mAuthInstance;

    @Inject
    public LoginRepoImpl(FirebaseAuth auth) {
        mAuthInstance = auth;
    }

    @Override
    public Single<AuthResult> login(final String email, final String password) {
        return RxFirebaseAuth.signInWithEmailAndPassword(mAuthInstance, email, password).toSingle();
    }
}
