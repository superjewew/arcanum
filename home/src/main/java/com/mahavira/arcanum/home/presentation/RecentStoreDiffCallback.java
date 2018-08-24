package com.mahavira.arcanum.home.presentation;

import android.support.v7.util.DiffUtil;
import java.util.List;

/**
 * Created by norman on 24/08/18.
 *
 * Used to check difference between recent stores data when updating RecentStoreAdaptera
 */

public class RecentStoreDiffCallback extends DiffUtil.Callback {

    private final List<String> mOldRecentStoreList;

    private final List<String> mNewRecentStoreList;

    RecentStoreDiffCallback(List<String> oldRecentStoreList, List<String> newRecentStoreList) {
        mOldRecentStoreList = oldRecentStoreList;
        mNewRecentStoreList = newRecentStoreList;
    }

    @Override
    public int getOldListSize() {
        return mOldRecentStoreList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewRecentStoreList.size();
    }

    @Override
    public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {
        return mOldRecentStoreList.get(oldItemPosition).equals(mNewRecentStoreList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
        return mOldRecentStoreList.get(oldItemPosition).equals(mNewRecentStoreList.get(newItemPosition));
    }
}
