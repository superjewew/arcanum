package com.mahavira.arcanum.login.di;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.login.data.LoginRepoImpl;
import com.mahavira.arcanum.login.domain.repo.LoginRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 13/07/18.
 *
 */

@Module(includes = {LoginBuilderModule.class})
public class LoginModule {

    @Provides
    @Singleton
    LoginRepository provideLoginRepository(FirebaseAuth firebaseAuth, FirebaseFirestore firestore) {
        return new LoginRepoImpl(firebaseAuth, firestore);
    }
}
