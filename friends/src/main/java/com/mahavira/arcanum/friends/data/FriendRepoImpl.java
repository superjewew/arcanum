package com.mahavira.arcanum.friends.data;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.friends.domain.repo.FriendRepository;
import com.mahavira.base.entity.User;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by norman on 25/08/18.
 */

public class FriendRepoImpl implements FriendRepository {

    private FirebaseFirestore mFirestore;

    @Inject
    public FriendRepoImpl(FirebaseFirestore firestore) {
        mFirestore = firestore;
    }

    @Override
    public Single<List<User>> getOnlineUser() {
        return null;
    }
}
