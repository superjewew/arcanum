package com.mahavira.arcanum.dashboard.presentation;

import static com.mahavira.arcanum.dashboard.presentation.DashboardActivity.NAV_FRIENDS_INDEX;
import static com.mahavira.arcanum.dashboard.presentation.DashboardActivity.NAV_HOME_INDEX;
import static com.mahavira.arcanum.dashboard.presentation.DashboardActivity.NAV_STORES_INDEX;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import com.mahavira.arcanum.dashboard.R;
import com.mahavira.base.presentation.BaseViewModel;
import javax.inject.Inject;

/**
 * Created by norman on 23/08/18.
 *
 */

public class DashboardViewModel extends BaseViewModel {

    private final MutableLiveData<Integer> mNavigationEvent = new MutableLiveData<>();

    @Inject
    DashboardViewModel() {

    }

    MutableLiveData<Integer> getNavigationEvent() {
        return mNavigationEvent;
    }

    public boolean onNavigationClicked(@NonNull MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.navigation_home) {
            mNavigationEvent.setValue(NAV_HOME_INDEX);
            return true;
        } else if (i == R.id.navigation_stores) {
            mNavigationEvent.setValue(NAV_STORES_INDEX);
            return true;
        } else if (i == R.id.navigation_friends) {
            mNavigationEvent.setValue(NAV_FRIENDS_INDEX);
            return true;
        }
        return false;
    }

}
