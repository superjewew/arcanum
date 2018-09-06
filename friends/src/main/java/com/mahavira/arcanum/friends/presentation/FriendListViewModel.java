package com.mahavira.arcanum.friends.presentation;

import android.arch.lifecycle.MutableLiveData;
import com.mahavira.arcanum.friends.domain.entity.AddFriendParam;
import com.mahavira.arcanum.friends.domain.usecase.AddFriendUseCase;
import com.mahavira.arcanum.friends.domain.usecase.GetOnlineFriendUseCase;
import com.mahavira.base.core.Resource;
import com.mahavira.base.entity.User;
import com.mahavira.base.presentation.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by norman on 25/08/18.
 *
 */

public class FriendListViewModel extends BaseViewModel {

    private final MutableLiveData<Resource<List<User>>> mOnlineFriends = new MutableLiveData<>();

    private final MutableLiveData<Resource<Boolean>> mAddFriendResult = new MutableLiveData<>();

    private GetOnlineFriendUseCase mGetOnlineFriendUseCase;

    private AddFriendUseCase mAddFriendUseCase;

    @Inject
    FriendListViewModel(GetOnlineFriendUseCase getOnlineFriendUseCase, AddFriendUseCase addFriendUseCase) {
        mGetOnlineFriendUseCase = getOnlineFriendUseCase;
        mAddFriendUseCase = addFriendUseCase;
    }

    MutableLiveData<Resource<List<User>>> getOnlineFriends() {
        return mOnlineFriends;
    }

    MutableLiveData<Resource<Boolean>> getAddFriendResult() {
        return mAddFriendResult;
    }

    void attemptGetOnlineFriends(String email) {
        mDisposable.add(mGetOnlineFriendUseCase.execute(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> doOnSubscribe())
                .doFinally(this::hideLoading)
                .subscribe(this::onGetOnlineFriendsSuccess, this::onGetOnlineFriendsFailed));
    }

    private void onGetOnlineFriendsSuccess(final List<User> users) {
        mOnlineFriends.setValue(Resource.success(users));
    }

    private void onGetOnlineFriendsFailed(final Throwable throwable) {
        mOnlineFriends.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }

    void attemptAddFriend(final String userEmail, final String friendToBeAdded) {
        AddFriendParam param = new AddFriendParam(userEmail, friendToBeAdded);
        mDisposable.add(mAddFriendUseCase.execute(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> doOnSubscribe())
                .doFinally(this::hideLoading)
                .subscribe(this::onAddFriendSuccess, this::onAddFriendFailed)
        );
    }

    private void onAddFriendSuccess() {
        mAddFriendResult.setValue(Resource.success(true));
    }

    private void onAddFriendFailed(final Throwable throwable) {
        mAddFriendResult.setValue(Resource.error(null, throwable.getLocalizedMessage(), false));
    }

}
