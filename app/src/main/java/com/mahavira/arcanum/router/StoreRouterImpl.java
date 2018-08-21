package com.mahavira.arcanum.router;

import static com.mahavira.arcanum.store.presentation.detail.StoreDetailActivity.STORE_EXTRA;

import android.content.Context;
import android.content.Intent;
import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.arcanum.store.presentation.StoreRouter;
import com.mahavira.arcanum.store.presentation.detail.StoreDetailActivity;
import org.parceler.Parcels;

/**
 * Created by norman on 20/08/18.
 */

public class StoreRouterImpl implements StoreRouter {

    @Override
    public void goToStoreDetail(final Context context, final Store store) {
        Intent intent = new Intent(context, StoreDetailActivity.class);
        intent.putExtra(STORE_EXTRA, Parcels.wrap(store));
        context.startActivity(intent);
    }

    @Override
    public void goToPlayHereConfirmation(final Context context) {

    }
}
