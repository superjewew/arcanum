package com.mahavira.arcanum.router;

import android.content.Context;
import android.content.Intent;
import com.mahavira.arcanum.login.presentation.LoginRouter;
import com.mahavira.arcanum.login.presentation.feature.register.RegisterActivity;
import com.mahavira.arcanum.store.presentation.StoreListActivity;

/**
 * Created by norman on 15/08/18.
 *
 */

public class LoginRouterImpl implements LoginRouter {

    @Override
    public void goToDashboard(final Context context) {
        Intent intent = new Intent(context, StoreListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void goToRegister(final Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}
