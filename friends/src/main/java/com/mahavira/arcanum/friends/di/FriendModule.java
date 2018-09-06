package com.mahavira.arcanum.friends.di;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.friends.data.FriendRepoImpl;
import com.mahavira.arcanum.friends.domain.repo.FriendRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by norman on 25/08/18.
 */

@Module(includes = {FriendBuilderModule.class})
public class FriendModule {

    @Provides
    @Singleton
    FriendRepository provideFriendRepository(FirebaseFirestore firestore) {
        return new FriendRepoImpl(firestore);
    }
}
