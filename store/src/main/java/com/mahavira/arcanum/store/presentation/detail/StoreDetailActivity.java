package com.mahavira.arcanum.store.presentation.detail;

import android.os.Bundle;
import com.mahavira.arcanum.store.R;
import com.mahavira.arcanum.store.databinding.ActivityStoreDetailBinding;
import com.mahavira.base.presentation.BaseActivity;

public class StoreDetailActivity extends BaseActivity<ActivityStoreDetailBinding, StoreDetailViewModel> {

    public static final String STORE_EXTRA = "store";

    @Override
    public int getViewModelBindingVariable() {
        return NO_VIEW_MODEL_BINDING_VARIABLE;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
