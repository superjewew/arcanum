package com.mahavira.base.core;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by norman on 16/08/18.
 *
 */

public abstract class BaseRecyclerAdapter<T, V extends ViewDataBinding>
        extends RecyclerView.Adapter<BaseViewHolder<V>> {

    private List<T> mDataList = new ArrayList<>();

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
        DiffUtil.Callback callback = getCallback(mDataList, newData);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);

        mDataList.clear();
        mDataList.addAll(newData);
        diffResult.dispatchUpdatesTo(this);
    }

    protected abstract DiffUtil.Callback getCallback(List<T> oldData, List<T> newData);

    protected abstract void bind(V binding, T data);

    protected abstract V inflateBinding(ViewGroup parent);

    public interface ItemClickListener<T> {
        void onItemClicked(T item);
    }

}
