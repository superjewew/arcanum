package com.mahavira.arcanum.home.presentation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;
import com.mahavira.arcanum.home.BR;
import com.mahavira.arcanum.home.R;
import com.mahavira.arcanum.home.databinding.FragmentHomeBinding;
import com.mahavira.base.presentation.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    private RecentStoreAdapter mAdapter;

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

        getViewModel().attemptGetRecentStore("");
    }

    private void setupAdapter() {
        mAdapter = new RecentStoreAdapter(getActivity());
    }

    private void setupRecyclerView(final RecentStoreAdapter adapter) {
        getDataBinding().recentStoreList.setLayoutManager(new LinearLayoutManager(getActivity()));
        getDataBinding().recentStoreList.setAdapter(adapter);
    }
}
