package com.mahavira.arcanum.product.presentation;

import android.os.Bundle;
import com.mahavira.arcanum.product.R;
import com.mahavira.arcanum.product.databinding.ActivityProductDetailBinding;
import com.mahavira.base.presentation.BaseActivity;

public class ProductDetailActivity extends BaseActivity<ActivityProductDetailBinding, ProductDetailViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return NO_VIEW_MODEL_BINDING_VARIABLE;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
