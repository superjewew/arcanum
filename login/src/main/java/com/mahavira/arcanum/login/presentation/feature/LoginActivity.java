package com.mahavira.arcanum.login.presentation.feature;

import android.os.Bundle;

import com.android.databinding.library.baseAdapters.BR;
import com.mahavira.arcanum.login.R;
import com.mahavira.arcanum.login.databinding.ActivityLoginBinding;
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
    }
}
