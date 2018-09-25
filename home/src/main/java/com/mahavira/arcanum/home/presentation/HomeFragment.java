package com.mahavira.arcanum.home.presentation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mahavira.arcanum.home.BR;
import com.mahavira.arcanum.home.R;
import com.mahavira.arcanum.home.databinding.FragmentHomeBinding;
import com.mahavira.arcanum.store.presentation.StoreRouter;
import com.mahavira.base.presentation.BaseFragment;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    private RecentStoreAdapter mAdapter;

    @Inject
    FirebaseAuth mFirebaseAuth;

    @Inject
    StoreRouter mStoreRouter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupAdapter();
        setupRecyclerView(mAdapter);

        getViewModel().getRecentStoreData().observe(this, listResource -> {
            if(listResource != null) {
                switch (listResource.status) {
                    case SUCCESS:
                        mAdapter.addData(listResource.data);
                        break;
                    case ERROR:
                        Toast.makeText(getActivity(), "Error loading recent stores", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getViewModel().getItemClicked().observe(this, store -> {
            assert store != null;
            mStoreRouter.goToStoreDetail(getActivity(), store);
        });

        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if(user != null) {
            getViewModel().attemptGetRecentStore(user.getEmail());
        }
    }

    private void setupAdapter() {
        mAdapter = new RecentStoreAdapter(getActivity(), getViewModel());
    }

    private void setupRecyclerView(final RecentStoreAdapter adapter) {
        getDataBinding().recentStoreList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        getDataBinding().recentStoreList.setAdapter(adapter);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public HomeFragment build() {
            return new HomeFragment();
        }
    }
}
