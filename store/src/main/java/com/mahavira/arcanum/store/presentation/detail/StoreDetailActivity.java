package com.mahavira.arcanum.store.presentation.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
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

        getViewModel().getPlayHereClickedEvent().observe(this, __ -> new IntentIntegrator(this).initiateScan());

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

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
