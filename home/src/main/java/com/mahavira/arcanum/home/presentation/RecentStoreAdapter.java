package com.mahavira.arcanum.home.presentation;

import android.content.Context;
import android.support.v7.util.DiffUtil.Callback;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.mahavira.arcanum.home.databinding.ItemRecentStoreListBinding;
import com.mahavira.base.core.BaseRecyclerAdapter;
import java.util.List;

/**
 * Created by norman on 23/08/18.
 *
 *
 */

public class RecentStoreAdapter extends BaseRecyclerAdapter<String, ItemRecentStoreListBinding> {

    private Context mContext;

    private final HomeViewModel mViewModel;

    RecentStoreAdapter(Context context, HomeViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
    }

    @Override
    protected Callback getCallback(final List<String> oldData, final List<String> newData) {
        return new RecentStoreDiffCallback(oldData, newData);
    }

    @Override
    protected void bind(final ItemRecentStoreListBinding binding, final String data) {
        ItemClickListener<String> listener = mViewModel::onItemClicked;
        binding.setStoreName(data);
        binding.setListener(listener);
    }

    @Override
    protected ItemRecentStoreListBinding inflateBinding(final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return ItemRecentStoreListBinding.inflate(inflater, parent, false);
    }

}
