package com.mahavira.arcanum.friends.data;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.friends.domain.entity.AddFriendParam;
import com.mahavira.arcanum.friends.domain.repo.FriendRepository;
import com.mahavira.base.core.BaseRepository;
import com.mahavira.base.entity.User;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by norman on 25/08/18.
 *
 */

public class FriendRepoImpl extends BaseRepository implements FriendRepository {

    private FirebaseFirestore mFirestore;

    @Inject
    public FriendRepoImpl(FirebaseFirestore firestore) {
        mFirestore = firestore;
    }

    @Override
    public Single<List<User>> getOnlineUser() {
        return getValue(mFirestore.collection(USER_COLLECTION), User.class).toSingle();
    }

    @Override
    public Single<User> getCurrentUser(String email) {
        return getValue(mFirestore.collection(USER_COLLECTION).document(email), User.class).toSingle();
    }

    @Override
    public Completable addFriend(final AddFriendParam param) {
        return getCurrentUser(param.getUserEmail()).flatMapCompletable(user -> {
            if(!param.isEmail()) {
                return query(mFirestore.collection(USER_COLLECTION), "name", param.getFriendToBeAdded(), User.class)
                        .flatMapCompletable(users -> {
                            User result = users.get(0);
                            user.addFriend(result.getEmail());
                            return setValue(mFirestore.collection(USER_COLLECTION).document(user.getEmail()), user);
                        });
            } else {
                return saveFriend(user, param.getFriendToBeAdded());
            }
        });
    }

    private Completable saveFriend(User user, String email) {
        user.addFriend(email);
        return setValue(mFirestore.collection(USER_COLLECTION).document(user.getEmail()), user);
    }
}
