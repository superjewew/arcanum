package com.mahavira.arcanum.product.di;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.product.data.ProductRepoImpl;
import com.mahavira.arcanum.product.domain.repo.ProductRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by norman on 24/08/18.
 *
 */

@Module(includes = {ProductBuilderModule.class})
public class ProductModule {

    @Provides
    @Singleton
    ProductRepository provideProductRepository(FirebaseFirestore firestore) {
        return new ProductRepoImpl(firestore);
    }
}
