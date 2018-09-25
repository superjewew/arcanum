package com.mahavira.arcanum.router;

import static com.mahavira.arcanum.product.presentation.ProductDetailActivity.PRODUCT_NAME_EXTRA;
import static com.mahavira.arcanum.store.presentation.detail.StoreDetailActivity.STORE_EXTRA;
import static com.mahavira.arcanum.store.presentation.detail.StoreDetailActivity.STORE_NAME_EXTRA;

import android.content.Context;
import android.content.Intent;
import com.mahavira.arcanum.product.presentation.ProductDetailActivity;
import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.arcanum.store.presentation.StoreRouter;
import com.mahavira.arcanum.store.presentation.detail.StoreDetailActivity;
import org.parceler.Parcels;

/**
 * Created by norman on 20/08/18.
 *
 */

public class StoreRouterImpl implements StoreRouter {

    @Override
    public void goToStoreDetail(final Context context, final Store store) {
        Intent intent = new Intent(context, StoreDetailActivity.class);
        intent.putExtra(STORE_EXTRA, Parcels.wrap(store));
        context.startActivity(intent);
    }

    @Override
    public void goToStoreDetail(final Context context, final String storeName) {
        Intent intent = new Intent(context, StoreDetailActivity.class);
        intent.putExtra(STORE_NAME_EXTRA, storeName);
        context.startActivity(intent);
    }

    @Override
    public void goToPlayHereConfirmation(final Context context) {

    }

    @Override
    public void goToProductDetail(final Context context, final String name) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(PRODUCT_NAME_EXTRA, name);
        context.startActivity(intent);
    }
}
