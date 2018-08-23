package com.mahavira.arcanum.home.data;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.home.domain.repo.HomeRepository;
import com.mahavira.base.core.BaseRepository;
import com.mahavira.base.entity.User;
import io.reactivex.Single;
import javax.inject.Inject;

/**
 * Created by norman on 23/08/18.
 *
 */

public class HomeRepoImpl extends BaseRepository implements HomeRepository {

    private static final String USERS_COLLECTION = "users";

    private FirebaseFirestore mFirestoreInstance;

    @Inject
    public HomeRepoImpl(FirebaseFirestore firestore) {
        mFirestoreInstance = firestore;
    }

    @Override
    public Single<User> getUserDetail(final String email) {
        return getValue(mFirestoreInstance.collection(USERS_COLLECTION).document(email), User.class).toSingle();
    }
}
