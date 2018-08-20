package com.mahavira.arcanum.login.data;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.login.domain.entity.User;
import com.mahavira.arcanum.login.domain.repo.LoginRepository;

import com.mahavira.base.core.BaseRepository;
import io.reactivex.Completable;
import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import io.reactivex.Single;

/**
 * Created by norman on 15/08/18.
 */

public class LoginRepoImpl extends BaseRepository implements LoginRepository {

    public static final String USERS_COLLECTION = "users";

    private FirebaseAuth mAuthInstance;

    private FirebaseFirestore mFirestoreInstance;

    @Inject
    public LoginRepoImpl(FirebaseAuth auth, FirebaseFirestore firestore) {
        mAuthInstance = auth;
        mFirestoreInstance = firestore;
    }

    @Override
    public Single<AuthResult> login(final String email, final String password) {
        return RxFirebaseAuth.signInWithEmailAndPassword(mAuthInstance, email, password).toSingle();
    }

    @Override
    public Completable register(final User user) {
        return setValue(mFirestoreInstance.collection(USERS_COLLECTION).document(user.getEmail()), user);
    }
}
