package com.mahavira.arcanum.login.presentation.feature.register;

import android.os.Bundle;
import android.widget.Toast;
import com.mahavira.arcanum.login.BR;
import com.mahavira.arcanum.login.R;
import com.mahavira.arcanum.login.databinding.ActivityRegisterBinding;
import com.mahavira.arcanum.login.domain.entity.Pass;
import com.mahavira.arcanum.login.domain.entity.User;
import com.mahavira.base.presentation.BaseActivity;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getViewModel().getRegisterResultData().observe(this, result -> {
            if(result != null) {
                switch (result.status) {
                    case SUCCESS:
                        Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show();
                        this.finish();
                        break;
                    case ERROR:
                        Toast.makeText(this, "Register Failed, " + result.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getDataBinding().setUser(new User());
        getDataBinding().setSecret(new Pass());
    }
}
