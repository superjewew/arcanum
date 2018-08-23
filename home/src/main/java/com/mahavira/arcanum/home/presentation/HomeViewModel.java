package com.mahavira.arcanum.home.presentation;

import android.arch.lifecycle.MutableLiveData;
import com.mahavira.base.presentation.BaseViewModel;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by norman on 23/08/18.
 */

public class HomeViewModel extends BaseViewModel {

    private final MutableLiveData<List<String>> mRecentStoreData = new MutableLiveData<>();

    @Inject
    HomeViewModel() {

    }

    void attemptGetRecentStore() {

    }

}
