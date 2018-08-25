package com.mahavira.arcanum.product.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.mahavira.arcanum.product.R;
import com.mahavira.arcanum.product.databinding.ActivityProductDetailBinding;
import com.mahavira.base.presentation.BaseActivity;
import com.mahavira.base.presentation.ExtraInjectable;

public class ProductDetailActivity extends BaseActivity<ActivityProductDetailBinding, ProductDetailViewModel>
        implements
        ExtraInjectable {

    public static final String PRODUCT_NAME_EXTRA = "product_name";

    private String mProductName;

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

        getViewModel().getBoardgameData().observe(this, boardgameResource -> {
            if (boardgameResource != null) {
                switch (boardgameResource.status) {
                    case SUCCESS:
                        getDataBinding().setProduct(boardgameResource.data);
                        break;
                    case ERROR:
                        Toast.makeText(this, "Failed loading boardgame data, " + boardgameResource.message,
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getViewModel().attemptGetProductByName(mProductName);
    }

    @Override
    public void injectExtras(@NonNull final Bundle extras) {
        if (extras.containsKey(PRODUCT_NAME_EXTRA)) {
            mProductName = extras.getString(PRODUCT_NAME_EXTRA);
        }
    }
}
