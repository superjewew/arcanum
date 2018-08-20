package com.mahavira.arcanum.store.presentation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.mahavira.arcanum.store.R;
import com.mahavira.arcanum.store.databinding.FragmentStoreBinding;
import com.mahavira.base.presentation.BaseFragment;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreListFragment extends BaseFragment<FragmentStoreBinding, StoreListViewModel> {

    @Inject
    StoreRouter mStoreRouter;

    private StoreListAdapter mAdapter;

    public StoreListFragment() {
        // Required empty public constructor
    }

    @Override
    public int getViewModelBindingVariable() {
        return NO_VIEW_MODEL_BINDING_VARIABLE;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_store;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupAdapter();
        setupRecyclerView(mAdapter);

        getViewModel().getStoreData().observe(this, listResource -> {
            if (listResource != null) {
                switch (listResource.status) {
                    case SUCCESS:
                        mAdapter.addData(listResource.data);
                        break;
                    case ERROR:
                        Toast.makeText(getActivity(), "Failed to get stores, " + listResource.message,
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getViewModel().getStoreClicked().observe(this, store -> mStoreRouter.goToStoreDetail(getActivity(), store));

        getViewModel().attemptGetPartner();
    }

    private void setupRecyclerView(final StoreListAdapter adapter) {
        getDataBinding().storeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        getDataBinding().storeList.setAdapter(adapter);
    }

    private void setupAdapter() {
        mAdapter = new StoreListAdapter(getActivity(), getViewModel());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public StoreListFragment build() {
            StoreListFragment fragment = new StoreListFragment();
            return fragment;
        }
    }
}
