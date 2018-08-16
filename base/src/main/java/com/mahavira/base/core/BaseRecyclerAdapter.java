package com.mahavira.base.core;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by norman on 16/08/18.
 */

public abstract class BaseRecyclerAdapter<T, V extends ViewDataBinding>
        extends RecyclerView.Adapter<BaseViewHolder<V>> {

    protected List<T> mDataList = new ArrayList<>();

    @NonNull
    @Override
    public BaseViewHolder<V> onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        V binding = inflateBinding(parent);

        return new BaseViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder<V> holder, final int position) {
        bind(holder.mBinding, mDataList.get(position));
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void addData(List<T> newData) {
        mDataList.addAll(newData);
        notifyDataSetChanged();
    }

    protected abstract void bind(V binding, T data);

    protected abstract V inflateBinding(ViewGroup parent);

}
