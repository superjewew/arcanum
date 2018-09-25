package com.mahavira.arcanum.store.presentation;

import android.content.Context;
import com.mahavira.arcanum.store.domain.entity.Store;

/**
 * Created by norman on 20/08/18.
 *
 */

public interface StoreRouter {

    void goToStoreDetail(Context context, Store store);

    void goToStoreDetail(Context context, String storeName);

    void goToPlayHereConfirmation(Context context);

    void goToProductDetail(Context context, String name);

}
