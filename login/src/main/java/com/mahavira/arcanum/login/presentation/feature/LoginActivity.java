package com.mahavira.arcanum.login.presentation.feature;

import android.os.Bundle;
import android.widget.Toast;
import com.android.databinding.library.baseAdapters.BR;
import com.mahavira.arcanum.login.R;
import com.mahavira.arcanum.login.databinding.ActivityLoginBinding;
import com.mahavira.arcanum.login.domain.entity.AuthParam;
import com.mahavira.base.presentation.BaseActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

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

        getViewModel().getLoginResultData().observe(this, authResultResource -> {
            if (authResultResource != null) {
                switch (authResultResource.status) {
                    case SUCCESS:
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR:
                        Toast.makeText(this, "Login Failed, " + authResultResource.message, Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
            }
        });

        getDataBinding().setParam(new AuthParam("", ""));

    }
}
