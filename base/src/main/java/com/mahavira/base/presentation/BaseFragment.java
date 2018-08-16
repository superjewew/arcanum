package com.mahavira.base.presentation;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * The base Fragment class using MVVM architecture together with DataBinding & Dagger compatibility.
 * Contains base functionality to prepare Fragment with MVVM, DataBinding & Dagger to reduce
 * boilerplate code. Set T for the DataBinding class associated with the Fragment's layout, and set
 * V for ViewModel associated with the Fragment.
 *
 * Created by bobbi on 21/04/18.
 */

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel>
        extends Fragment {

    protected static final int NO_VIEW_MODEL_BINDING_VARIABLE = -1;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private T mDataBinding;

    private V mViewModel;

    /**
     * Override for set view model binding variable
     * return {@value #NO_VIEW_MODEL_BINDING_VARIABLE} in case no need to bind ViewModel into
     * DataBinding, which mean there is no ViewModel variable declaration on Activity layout.
     *
     * @return variable id, generated on BR(Binding Resource) class, e.g : BR.viewModel
     */
    public abstract int getViewModelBindingVariable();

    /**
     * Override to set Activity layout resource id
     *
     * @return layout resource id, generated on R(Resource) class, e.g : R.layout.main_menu_activity
     */
    @LayoutRes
    public abstract int getLayoutId();

    /**
     * The method return DataBinding instance
     *
     * @return T generic type of DataBinding
     */
    public final T getDataBinding() {
        return mDataBinding;
    }

    /**
     * The method return ViewModel instance
     *
     * @return V generic type of ViewModel
     */
    public final V getViewModel() {
        return mViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        if (this instanceof ExtraInjectable && getArguments() != null) {
            ((ExtraInjectable) this).injectExtras(getArguments());
        }
        super.onCreate(savedInstanceState);
        provideViewModel();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getViewModelBindingVariable() != NO_VIEW_MODEL_BINDING_VARIABLE) {
            mDataBinding.setVariable(getViewModelBindingVariable(), mViewModel);
            mDataBinding.executePendingBindings();
        }
    }

    private void provideViewModel() {
        Class<V> clazz = getViewModelClass(getClass());
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(clazz);
    }

    private Class<V> getViewModelClass(Class<?> aClass) {
        Type type = aClass.getGenericSuperclass();

        if (type instanceof ParameterizedType) {
            //noinspection unchecked
            return (Class<V>) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            return getViewModelClass(aClass.getSuperclass());
        }
    }
}
