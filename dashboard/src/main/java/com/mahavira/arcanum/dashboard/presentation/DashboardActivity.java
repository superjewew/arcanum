package com.mahavira.arcanum.dashboard.presentation;

import android.os.Bundle;
import com.mahavira.arcanum.dashboard.BR;
import com.mahavira.arcanum.dashboard.R;
import com.mahavira.arcanum.dashboard.databinding.ActivityDashboardBinding;
import com.mahavira.base.presentation.BaseActivity;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
