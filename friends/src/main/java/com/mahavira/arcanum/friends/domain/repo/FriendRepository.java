package com.mahavira.arcanum.friends.domain.repo;

import com.mahavira.base.entity.User;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by norman on 25/08/18.
 */

public interface FriendRepository {

    Single<List<User>> getOnlineUser();

}
