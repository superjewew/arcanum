package com.mahavira.arcanum.store.presentation.detail;

import android.content.Context;
import android.support.v7.util.DiffUtil.Callback;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.mahavira.arcanum.store.databinding.ItemStoreDetailGameListBinding;
import com.mahavira.base.core.BaseRecyclerAdapter;
import java.util.List;

/**
 * Created by norman on 20/08/18.
 *
 *
 */

public class StoreDetailAdapter extends BaseRecyclerAdapter<String, ItemStoreDetailGameListBinding> {

    private Context mContext;

    private StoreDetailViewModel mViewModel;

    StoreDetailAdapter(Context context, StoreDetailViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
    }

    @Override
    protected Callback getCallback(final List<String> oldData, final List<String> newData) {
        return new StoreDetailDiffCallback(oldData, newData);
    }

    @Override
    protected void bind(final ItemStoreDetailGameListBinding binding, final String data) {
        ItemClickListener<String> listener = item -> mViewModel.productClicked(item);
        binding.setProduct(data);
        binding.setListener(listener);
    }

    @Override
    protected ItemStoreDetailGameListBinding inflateBinding(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return ItemStoreDetailGameListBinding.inflate(inflater, parent, false);
    }
}
