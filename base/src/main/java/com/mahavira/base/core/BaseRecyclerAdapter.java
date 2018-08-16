package com.mahavira.base.core;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.mahavira.base.core.BaseRecyclerAdapter.BaseViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by norman on 16/08/18.
 */

public abstract class BaseRecyclerAdapter<T, V extends ViewDataBinding> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> mDataList = new ArrayList<>();

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        V binding = inflateBinding(parent);

        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder holder, final int position) {
        T data = mDataList.get(position);

        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void addData(List<T> newData) {
        mDataList.addAll(newData);
        notifyDataSetChanged();
    }

    protected abstract int getVariableId();

    protected abstract V inflateBinding(ViewGroup parent);

    class BaseViewHolder extends ViewHolder {

        private V mBinding;

        public BaseViewHolder(final V binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(T data) {
            mBinding.setVariable(getVariableId(), data);
            mBinding.executePendingBindings();
        }
    }
}
