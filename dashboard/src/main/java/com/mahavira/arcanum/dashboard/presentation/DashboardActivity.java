package com.mahavira.arcanum.dashboard.presentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.mahavira.arcanum.dashboard.BR;
import com.mahavira.arcanum.dashboard.R;
import com.mahavira.arcanum.dashboard.databinding.ActivityDashboardBinding;
import com.mahavira.arcanum.dashboard.presentation.view.DashboardBottomDrawerFragment;
import com.mahavira.arcanum.friends.presentation.FriendListFragment;
import com.mahavira.arcanum.home.presentation.HomeFragment;
import com.mahavira.arcanum.store.presentation.StoreListFragment;
import com.mahavira.base.presentation.BaseActivity;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardViewModel> {

    public static final int NAV_HOME_INDEX = 0;

    public static final int NAV_STORES_INDEX = 1;

    public static final int NAV_FRIENDS_INDEX = 2;

    final Fragment fragment1 = HomeFragment.builder().build();

    final Fragment fragment2 = StoreListFragment.builder().build();

    final Fragment fragment3 = FriendListFragment.builder().build();

    Fragment active = fragment1;

    private final FragmentManager mFragmentManager = getSupportFragmentManager();

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

        mFragmentManager.beginTransaction().add(R.id.container, fragment3, "3").hide(fragment3).commit();
        mFragmentManager.beginTransaction().add(R.id.container, fragment2, "2").hide(fragment2).commit();
        mFragmentManager.beginTransaction().add(R.id.container, fragment1, "1").commit();

        getViewModel().getNavigationEvent().observe(this, navIndex -> {
            if (navIndex != null) {
                switch (navIndex) {
                    case NAV_HOME_INDEX:
                        showNewFragment(fragment1);
                        break;
                    case NAV_STORES_INDEX:
                        showNewFragment(fragment2);
                        break;
                    case NAV_FRIENDS_INDEX:
                        showNewFragment(fragment3);
                        break;
                }
            }
        });

        setupBottomDrawer();
    }

    private void showNewFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(active).show(fragment).commit();
        active = fragment;
    }

    private void setupBottomDrawer() {
        getDataBinding().bar.setNavigationOnClickListener(v -> {
            DashboardBottomDrawerFragment fragment = DashboardBottomDrawerFragment.newInstance(getViewModel());
            fragment.show(getSupportFragmentManager(), fragment.getTag());
        });
        getDataBinding().bar.setNavigationIcon(R.drawable.ic_navigation_menu_24dp);
    }
}
