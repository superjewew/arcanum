package com.mahavira.arcanum.home.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.mahavira.arcanum.home.databinding.ItemRecentStoreListBinding;
import com.mahavira.base.core.BaseRecyclerAdapter;

/**
 * Created by norman on 23/08/18.
 */

public class RecentStoreAdapter extends BaseRecyclerAdapter<String, ItemRecentStoreListBinding> {

    private Context mContext;

    RecentStoreAdapter(Context context) {
        mContext = context;
    }

    @Override
    protected void bind(final ItemRecentStoreListBinding binding, final String data) {
        binding.setStoreName(data);
    }

    @Override
    protected ItemRecentStoreListBinding inflateBinding(final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return ItemRecentStoreListBinding.inflate(inflater, parent, false);
    }

}
