package com.mahavira.arcanum.store.di;

import android.content.Context;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.store.data.StoreRepoImpl;
import com.mahavira.arcanum.store.domain.entity.VisitNotificationManager;
import com.mahavira.arcanum.store.domain.repo.StoreRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by norman on 16/08/18.
 */

@Module(includes = {StoreBuilderModule.class})
public class StoreModule {

    @Provides
    @Singleton
    StoreRepository provideStoreRepository(FirebaseFirestore instance) {
        return new StoreRepoImpl(instance);
    }

    @Provides
    VisitNotificationManager provideManager(Context context) {
        return new VisitNotificationManager(context);
    }

}
