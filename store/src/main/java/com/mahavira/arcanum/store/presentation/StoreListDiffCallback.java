package com.mahavira.arcanum.store.presentation;

import android.support.v7.util.DiffUtil;
import com.mahavira.arcanum.store.domain.entity.Store;
import java.util.List;

/**
 * Created by norman on 24/08/18.
 *
 * Used to check difference between store list data when updating StoreListAdapter
 */

public class StoreListDiffCallback extends DiffUtil.Callback {

    private final List<Store> mOldStoreList;

    private final List<Store> mNewStoreList;

    StoreListDiffCallback(List<Store> oldStoreList, List<Store> newStoreList) {
        mOldStoreList = oldStoreList;
        mNewStoreList = newStoreList;
    }

    @Override
    public int getOldListSize() {
        return mOldStoreList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewStoreList.size();
    }

    @Override
    public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {
        return mOldStoreList.get(oldItemPosition).getEmail().equals(mNewStoreList.get(newItemPosition).getEmail());
    }

    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
        return mOldStoreList.get(oldItemPosition).getName().equals(mNewStoreList.get(newItemPosition).getName());
    }
}
