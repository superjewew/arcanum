package com.mahavira.arcanum.friends.presentation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
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

    private OnlineFriendListAdapter mOnlineAdapter;

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
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupAdapters();
        setupRecyclerViews();

        getViewModel().getOnlineFriends().observe(this, listResource -> {
            if(listResource != null) {
                switch (listResource.status) {
                    case SUCCESS:
                        mOnlineAdapter.addUserData(listResource.data);
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

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.menu_friends, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setupRecyclerViews() {
        getDataBinding().currentlyPlayingList.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        getDataBinding().currentlyPlayingList.setAdapter(mOnlineAdapter);
    }

    private void setupAdapters() {
        mOnlineAdapter = new OnlineFriendListAdapter(getActivity());
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
