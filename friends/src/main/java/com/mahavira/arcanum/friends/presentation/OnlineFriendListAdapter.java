package com.mahavira.arcanum.friends.presentation;

import android.content.Context;
import android.support.v7.util.DiffUtil.Callback;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.mahavira.arcanum.friends.databinding.OnlineFriendListItemBinding;
import com.mahavira.arcanum.friends.domain.entity.OnlineUser;
import com.mahavira.base.core.BaseRecyclerAdapter;
import com.mahavira.base.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by norman on 25/08/18.
 *
 */

public class OnlineFriendListAdapter extends BaseRecyclerAdapter<OnlineUser, OnlineFriendListItemBinding> {

    private Context mContext;

    OnlineFriendListAdapter(Context context) {
        mContext = context;
    }

    @Override
    protected Callback getCallback(final List<OnlineUser> oldData, final List<OnlineUser> newData) {
        return new OnlineFriendDiffCallback(oldData, newData);
    }

    @Override
    protected void bind(final OnlineFriendListItemBinding binding, final OnlineUser data) {
        binding.setUser(data);
    }

    @Override
    protected OnlineFriendListItemBinding inflateBinding(final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return OnlineFriendListItemBinding.inflate(inflater, parent, false);
    }

    void addUserData(List<User> users) {
        List<OnlineUser> onlineUsers = new ArrayList<>();
        for (User user : users) {
            OnlineUser onlineUser = new OnlineUser();
            onlineUser.setName(user.getName());
            onlineUser.setWhere(user.getPlayingAt());
            onlineUsers.add(onlineUser);
        }

        addData(onlineUsers);
    }

}
