package com.mahavira.arcanum.product.di;

import android.arch.lifecycle.ViewModel;
import com.mahavira.arcanum.product.presentation.ProductDetailActivity;
import com.mahavira.arcanum.product.presentation.ProductDetailViewModel;
import com.mahavira.base.di.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by norman on 24/08/18.
 */

@Module
public abstract class ProductBuilderModule {

    @ContributesAndroidInjector
    abstract ProductDetailActivity bindProductDetailActivity();

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailViewModel.class)
    abstract ViewModel bindProductDetailViewModel(ProductDetailViewModel productDetailViewModel);
}
