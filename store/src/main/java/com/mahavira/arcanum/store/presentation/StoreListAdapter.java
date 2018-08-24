package com.mahavira.arcanum.store.presentation;

import android.content.Context;
import android.support.v7.util.DiffUtil.Callback;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.mahavira.arcanum.store.databinding.ItemStoreListBinding;
import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.base.core.BaseRecyclerAdapter;
import java.util.List;

/**
 * Created by norman on 16/08/18.
 *
 */

public class StoreListAdapter extends BaseRecyclerAdapter<Store, ItemStoreListBinding> {

    private Context mContext;

    private StoreListViewModel mViewModel;

    StoreListAdapter(Context context, StoreListViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
    }

    @Override
    protected Callback getCallback(final List<Store> oldData, final List<Store> newData) {
        return new StoreListDiffCallback(oldData, newData);
    }

    @Override
    protected void bind(final ItemStoreListBinding binding, final Store data) {
        binding.setStore(data);
        ItemClickListener<Store> clickListener = item -> mViewModel.onStoreClicked(item);
        binding.setListener(clickListener);
    }

    @Override
    protected ItemStoreListBinding inflateBinding(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return ItemStoreListBinding.inflate(inflater, parent, false);
    }
}
