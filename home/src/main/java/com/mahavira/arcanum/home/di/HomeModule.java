package com.mahavira.arcanum.home.di;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.home.data.HomeRepoImpl;
import com.mahavira.arcanum.home.domain.repo.HomeRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by norman on 23/08/18.
 *
 */

@Module(includes = {HomeBuilderModule.class})
public class HomeModule {

    @Provides
    @Singleton
    HomeRepository provideHomeRepository(FirebaseFirestore firestore) {
        return new HomeRepoImpl(firestore);
    }

}
