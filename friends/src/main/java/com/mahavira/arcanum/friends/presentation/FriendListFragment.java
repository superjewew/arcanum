package com.mahavira.arcanum.friends.presentation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mahavira.arcanum.friends.R;
import com.mahavira.arcanum.friends.databinding.FragmentFriendListBinding;
import com.mahavira.base.presentation.BaseFragment;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendListFragment extends BaseFragment<FragmentFriendListBinding, FriendListViewModel> {

    @Inject
    FirebaseAuth mAuth;

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

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getViewModel().getOnlineFriends().observe(this, listResource -> {
            if(listResource != null) {
                switch (listResource.status) {
                    case SUCCESS:
                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR:
                        Toast.makeText(getActivity(), "Error loading friends", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null) {
            getViewModel().attemptGetOnlineFriends(user.getEmail());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public FriendListFragment build() {
            return new FriendListFragment();
        }
    }
}
