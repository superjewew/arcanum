package com.mahavira.arcanum.store.presentation.detail;

import com.mahavira.base.core.SingleLiveEvent;
import com.mahavira.base.presentation.BaseViewModel;
import javax.inject.Inject;

/**
 * Created by norman on 20/08/18.
 */

public class StoreDetailViewModel extends BaseViewModel {

    private final SingleLiveEvent<Void> mPlayHereClickedEvent = new SingleLiveEvent<>();

    @Inject
    public StoreDetailViewModel() {

    }

    public SingleLiveEvent<Void> getPlayHereClickedEvent() {
        return mPlayHereClickedEvent;
    }

    public void playHere() {
        mPlayHereClickedEvent.call();
    }

}
