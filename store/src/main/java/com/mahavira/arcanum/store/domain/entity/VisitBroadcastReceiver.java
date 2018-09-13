package com.mahavira.arcanum.store.domain.entity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by norman on 13/09/18.
 */

public class VisitBroadcastReceiver extends BroadcastReceiver {

    public static final String PARAM_VISIT_USER_EXTRA = "param_user_visit";

    public static final String PARAM_VISIT_STORE_EXTRA = "param_store_visit";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        String userParam = intent.getStringExtra(PARAM_VISIT_USER_EXTRA);
        String storeParam = intent.getStringExtra(PARAM_VISIT_STORE_EXTRA);

        Log.d("VISIT_BROADCAST", userParam + ", " + storeParam);
    }
}
