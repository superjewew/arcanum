package com.mahavira.arcanum.login.presentation.feature;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import com.mahavira.arcanum.login.BR;
import com.mahavira.arcanum.login.R;
import com.mahavira.arcanum.login.databinding.ActivityLoginBinding;
import com.mahavira.arcanum.login.domain.entity.AuthParam;
import com.mahavira.arcanum.login.presentation.LoginRouter;
import com.mahavira.base.presentation.BaseActivity;
import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Inject
    LoginRouter mRouter;

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createNotificationChannel();

        getViewModel().getLoginResultData().observe(this, authResultResource -> {
            if (authResultResource != null) {
                switch (authResultResource.status) {
                    case SUCCESS:
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                        mRouter.goToDashboard(this);
                        break;
                    case ERROR:
                        Toast.makeText(this, "Login Failed, " + authResultResource.message, Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
            }
        });

        getViewModel().getRegisterEvent().observe(this, __ -> mRouter.goToRegister(this));

        getDataBinding().setParam(new AuthParam("", ""));

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.channel_id), name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
