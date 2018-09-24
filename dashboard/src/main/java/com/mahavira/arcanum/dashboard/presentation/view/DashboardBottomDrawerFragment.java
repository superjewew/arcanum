package com.mahavira.arcanum.dashboard.presentation.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mahavira.arcanum.dashboard.R;
import com.mahavira.arcanum.dashboard.presentation.DashboardViewModel;

/**
 * Created by norman on 24/09/18.
 *
 */

public class DashboardBottomDrawerFragment extends BottomSheetDialogFragment {

    public static final String EMAIL_EXTRA = "email";

    public static final String FULLNAME_EXTRA = "fullname";

    private static DashboardViewModel mViewModel;

    private String mEmail;

    private String mFullname;

    public static DashboardBottomDrawerFragment newInstance(DashboardViewModel viewModel, String email, String fullname) {
        mViewModel = viewModel;
        DashboardBottomDrawerFragment f = new DashboardBottomDrawerFragment();

        Bundle args = new Bundle();
        args.putString(EMAIL_EXTRA, email);
        args.putString(FULLNAME_EXTRA, fullname);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        assert args != null;
        mEmail = args.getString(EMAIL_EXTRA);
        mFullname = args.getString(FULLNAME_EXTRA);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bottom_drawer, container, false);

        NavigationView navigationView = view.findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            mViewModel.onNavigationClicked(menuItem);
            dismiss();
            return true;
        });

        TextView emailTv = view.findViewById(R.id.email_tv);
        TextView fullnameTv = view.findViewById(R.id.fullname_tv);

        emailTv.setText(mEmail);
        fullnameTv.setText(mFullname);
        return view;
    }
}
