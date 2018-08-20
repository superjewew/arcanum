package com.mahavira.arcanum.store.presentation.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import com.mahavira.arcanum.store.BR;
import com.mahavira.arcanum.store.R;
import com.mahavira.arcanum.store.databinding.ActivityStoreDetailBinding;
import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.arcanum.store.presentation.StoreRouter;
import com.mahavira.base.presentation.BaseActivity;
import com.mahavira.base.presentation.ExtraInjectable;
import javax.inject.Inject;
import org.parceler.Parcels;

public class StoreDetailActivity extends BaseActivity<ActivityStoreDetailBinding, StoreDetailViewModel> implements
        ExtraInjectable {

    public static final String STORE_EXTRA = "store";

    private StoreDetailAdapter mAdapter;

    private Store mStore;

    @Inject
    StoreRouter mStoreRouter;

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDataBinding().setStore(mStore);

        setupAdapter();
        setupRecyclerView(mAdapter);

        updateAvailableGames();

        getViewModel().getPlayHereClickedEvent().observe(this, __ -> mStoreRouter.goToPlayHereConfirmation(this));

    }

    private void updateAvailableGames() {
        mAdapter.addData(mStore.getBorrowedGames());
        mAdapter.notifyDataSetChanged();
    }

    private void setupAdapter() {
        mAdapter = new StoreDetailAdapter(this);
    }

    private void setupRecyclerView(final StoreDetailAdapter adapter) {
        getDataBinding().storeGameList.setLayoutManager(new GridLayoutManager(this, 3));
        getDataBinding().storeGameList.setAdapter(adapter);
    }

    @Override
    public void injectExtras(@NonNull final Bundle extras) {
        if (extras.containsKey(STORE_EXTRA)) {
            mStore = Parcels.unwrap(extras.getParcelable(STORE_EXTRA));
        }
    }
}
