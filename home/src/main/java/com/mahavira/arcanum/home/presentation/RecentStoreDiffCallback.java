package com.mahavira.arcanum.home.presentation;

import android.support.v7.util.DiffUtil;
import com.mahavira.arcanum.store.domain.entity.Store;
import java.util.List;

/**
 * Created by norman on 24/08/18.
 *
 * Used to check difference between recent stores data when updating RecentStoreAdaptera
 */

public class RecentStoreDiffCallback extends DiffUtil.Callback {

    private final List<Store> mOldRecentStoreList;

    private final List<Store> mNewRecentStoreList;

    RecentStoreDiffCallback(List<Store> oldRecentStoreList, List<Store> newRecentStoreList) {
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
