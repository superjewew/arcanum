package com.mahavira.arcanum.friends.domain.repo;

import com.mahavira.arcanum.friends.domain.entity.AddFriendParam;
import com.mahavira.base.entity.User;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by norman on 25/08/18.
 *
 */

public interface FriendRepository {

    Completable addFriend(AddFriendParam param);

    Single<List<User>> getOnlineUser();

    Single<User> getCurrentUser(String email);
}
