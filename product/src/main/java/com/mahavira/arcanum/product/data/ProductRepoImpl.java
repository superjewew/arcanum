package com.mahavira.arcanum.product.data;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.product.domain.entity.Boardgame;
import com.mahavira.arcanum.product.domain.repo.ProductRepository;
import com.mahavira.base.core.BaseRepository;
import io.reactivex.Single;
import javax.inject.Inject;

/**
 * Created by norman on 24/08/18.
 *
 */

public class ProductRepoImpl extends BaseRepository implements ProductRepository {

    private FirebaseFirestore mFirestore;

    @Inject
    public ProductRepoImpl(FirebaseFirestore firestore) {
        mFirestore = firestore;
    }

    @Override
    public Single<Boardgame> getProductByName(final String param) {
        return getValue(mFirestore.collection("products").document(param), Boardgame.class)
                .toSingle();
    }
}
