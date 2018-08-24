package com.mahavira.arcanum.dashboard.presentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.mahavira.arcanum.dashboard.BR;
import com.mahavira.arcanum.dashboard.R;
import com.mahavira.arcanum.dashboard.databinding.ActivityDashboardBinding;
import com.mahavira.arcanum.home.presentation.HomeFragment;
import com.mahavira.arcanum.store.presentation.StoreListFragment;
import com.mahavira.base.presentation.BaseActivity;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardViewModel> {

    public static final int NAV_HOME_INDEX = 0;

    public static final int NAV_STORES_INDEX = 1;

    public static final int NAV_FRIENDS_INDEX = 2;

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

        getViewModel().getNavigationEvent().observe(this, navIndex -> {
            if(navIndex != null) {
                switch (navIndex) {
                    case NAV_HOME_INDEX:
                        openFragment(HomeFragment.builder().build());
                        break;
                    case NAV_STORES_INDEX:
                        openFragment(StoreListFragment.builder().build());
                        break;
                    case NAV_FRIENDS_INDEX:
                        break;
                }
            }
        });
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit();
    }
}
