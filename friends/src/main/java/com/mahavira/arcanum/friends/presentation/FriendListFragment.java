package com.mahavira.arcanum.friends.presentation;


import android.support.v4.app.Fragment;
import com.mahavira.arcanum.friends.R;
import com.mahavira.arcanum.friends.databinding.FragmentFriendListBinding;
import com.mahavira.base.presentation.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendListFragment extends BaseFragment<FragmentFriendListBinding, FriendListViewModel> {


    public FriendListFragment() {
        // Required empty public constructor
    }


    @Override
    public int getViewModelBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_friend_list;
    }

}
