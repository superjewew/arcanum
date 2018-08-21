package com.mahavira.base.core;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView.ViewHolder;

public class BaseViewHolder<VB extends ViewDataBinding> extends ViewHolder {

    final VB mBinding;

    BaseViewHolder(final VB binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

}