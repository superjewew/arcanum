package com.mahavira.arcanum.dashboard.presentation;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;

/**
 * Created by norman on 23/08/18.
 *
 */

public class DashboardBindingAdapter {

    @BindingAdapter("onNavigationItemClicked")
    public static void setOnNavigationItemClicked(BottomNavigationView view,
            OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

}
