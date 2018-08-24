package com.mahavira.arcanum.store.presentation.detail;

import android.support.v7.util.DiffUtil;
import java.util.List;

/**
 * Created by norman on 24/08/18.
 *
 * Used to check difference between available games list on Store Detail activity
 */

public class StoreDetailDiffCallback extends DiffUtil.Callback {

    private final List<String> mOldStoreDetailList;

    private final List<String> mNewStoreDetailList;

    StoreDetailDiffCallback(List<String> oldStoreDetailList, List<String> newStoreDetailList) {
        mOldStoreDetailList = oldStoreDetailList;
        mNewStoreDetailList = newStoreDetailList;
    }

    @Override
    public int getOldListSize() {
        return mOldStoreDetailList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewStoreDetailList.size();
    }

    @Override
    public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {
        return mOldStoreDetailList.get(oldItemPosition).equals(mNewStoreDetailList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
        return mOldStoreDetailList.get(oldItemPosition).equals(mNewStoreDetailList.get(newItemPosition));
    }
}
