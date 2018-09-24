package com.mahavira.arcanum.login.presentation.feature.register;

import android.os.Bundle;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.mahavira.arcanum.login.BR;
import com.mahavira.arcanum.login.R;
import com.mahavira.arcanum.login.databinding.ActivityRegisterBinding;
import com.mahavira.arcanum.login.domain.entity.Pass;
import com.mahavira.base.entity.User;
import com.mahavira.base.presentation.BaseActivity;
import javax.inject.Inject;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {

    @Inject
    FirebaseAuth mAuth;

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
            if (result != null) {
                switch (result.status) {
                    case SUCCESS:
                        assert result.data != null;
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(result.data.getName()).build();
                            user.updateProfile(request);
                        }

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
