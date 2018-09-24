package com.mahavira.arcanum.dashboard.presentation.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mahavira.arcanum.dashboard.R;
import com.mahavira.arcanum.dashboard.presentation.DashboardViewModel;

/**
 * Created by norman on 24/09/18.
 *
 */

public class DashboardBottomDrawerFragment extends BottomSheetDialogFragment {

    private static DashboardViewModel mViewModel;

    public static DashboardBottomDrawerFragment newInstance(DashboardViewModel viewModel) {
        mViewModel = viewModel;
        return new DashboardBottomDrawerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bottom_drawer, container, false);

        NavigationView navigationView = view.findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            mViewModel.onNavigationClicked(menuItem);
            return true;
        });

        return view;
    }
}
