package com.mahavira.arcanum.store.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.mahavira.arcanum.store.BR;
import com.mahavira.arcanum.store.databinding.ItemStoreListBinding;
import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.base.core.BaseRecyclerAdapter;

/**
 * Created by norman on 16/08/18.
 */

public class StoreListAdapter extends BaseRecyclerAdapter<Store, ItemStoreListBinding> {

    private Context mContext;

    StoreListAdapter(Context context) {
        mContext = context;
    }

    @Override
    protected int getVariableId() {
        return BR.store;
    }

    @Override
    protected ItemStoreListBinding inflateBinding(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return ItemStoreListBinding.inflate(inflater, parent, false);
    }
}
