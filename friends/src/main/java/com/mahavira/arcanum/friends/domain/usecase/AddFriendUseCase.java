package com.mahavira.arcanum.friends.domain.usecase;

import com.mahavira.arcanum.friends.domain.entity.AddFriendParam;
import com.mahavira.arcanum.friends.domain.repo.FriendRepository;
import com.mahavira.base.core.CompletableUseCase;
import io.reactivex.Completable;
import javax.inject.Inject;

/**
 * Created by norman on 06/09/18.
 *
 */

public class AddFriendUseCase implements CompletableUseCase<AddFriendParam> {

    private FriendRepository mRepository;

    @Inject
    AddFriendUseCase(FriendRepository repository) {
        mRepository = repository;
    }

    @Override
    public Completable execute(final AddFriendParam param) {
        return mRepository.addFriend(param);
    }
}
