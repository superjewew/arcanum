package com.mahavira.arcanum.home.presentation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.mahavira.arcanum.home.BR;
import com.mahavira.arcanum.home.R;
import com.mahavira.arcanum.home.databinding.FragmentHomeBinding;
import com.mahavira.base.presentation.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
