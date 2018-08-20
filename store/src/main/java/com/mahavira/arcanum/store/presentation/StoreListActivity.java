package com.mahavira.arcanum.store.presentation;

import android.os.Bundle;
import com.mahavira.arcanum.store.R;
import com.mahavira.arcanum.store.databinding.ActivityStoreBinding;
import com.mahavira.base.presentation.BaseActivity;
import com.mahavira.base.presentation.BaseViewModel;

public class StoreListActivity extends BaseActivity<ActivityStoreBinding, BaseViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return NO_VIEW_MODEL_BINDING_VARIABLE;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_store;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, StoreListFragment.builder().build()).commit();
    }
}
