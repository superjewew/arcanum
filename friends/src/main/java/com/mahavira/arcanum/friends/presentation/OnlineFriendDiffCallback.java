package com.mahavira.arcanum.friends.presentation;

import android.support.v7.util.DiffUtil;
import com.mahavira.arcanum.friends.domain.entity.OnlineUser;
import java.util.List;

/**
 * Created by norman on 25/08/18.
 *
 */

public class OnlineFriendDiffCallback extends DiffUtil.Callback {

    private final List<OnlineUser> mOldOnlineUser;

    private final List<OnlineUser> mNewOnlineUser;

    OnlineFriendDiffCallback(List<OnlineUser> oldOnlineUser, List<OnlineUser> newOnlineUser) {
        mOldOnlineUser = oldOnlineUser;
        mNewOnlineUser = newOnlineUser;
    }

    @Override
    public int getOldListSize() {
        return mOldOnlineUser.size();
    }

    @Override
    public int getNewListSize() {
        return mNewOnlineUser.size();
    }

    @Override
    public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {
        return mOldOnlineUser.get(oldItemPosition) == mNewOnlineUser.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
        return mOldOnlineUser.get(oldItemPosition).getName().equals(mNewOnlineUser.get(newItemPosition).getName());
    }
}
