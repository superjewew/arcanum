package com.mahavira.arcanum.friends.domain.usecase;

import com.mahavira.arcanum.friends.domain.repo.FriendRepository;
import com.mahavira.base.core.BaseUseCaseWithParam;
import com.mahavira.base.entity.User;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by norman on 25/08/18.
 */

public class GetOnlineFriendUseCase implements BaseUseCaseWithParam<String, List<User>> {

    private FriendRepository mRepository;

    @Inject
    GetOnlineFriendUseCase(FriendRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<List<User>> execute(String email) {
        return mRepository.getOnlineUser().zipWith(mRepository.getCurrentUser(email), (users, user) -> filterOnlyFriend(users, user.getFriends()));
    }

    private List<User> filterOnlyFriend(List<User> users, List<String> friends) {
        List<User> usersToBeRemoved = new ArrayList<>();
        for (User user : users) {
            if (!friends.contains(user.getEmail())) {
                usersToBeRemoved.add(user);
            }
        }
        users.removeAll(usersToBeRemoved);
        return users;
    }
}
