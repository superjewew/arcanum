package com.mahavira.arcanum.router;

import android.content.Context;
import android.content.Intent;
import com.mahavira.arcanum.dashboard.presentation.DashboardActivity;
import com.mahavira.arcanum.login.presentation.LoginRouter;
import com.mahavira.arcanum.login.presentation.feature.register.RegisterActivity;

/**
 * Created by norman on 15/08/18.
 *
 */

public class LoginRouterImpl implements LoginRouter {

    @Override
    public void goToDashboard(final Context context) {
        Intent intent = new Intent(context, DashboardActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void goToRegister(final Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}
