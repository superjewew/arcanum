package com.mahavira.arcanum.store.data;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.arcanum.store.domain.repo.StoreRepository;
import com.mahavira.base.core.BaseRepository;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by norman on 16/08/18.
 */

public class StoreRepoImpl extends BaseRepository implements StoreRepository {

    private static final String STORE_COLLECTION = "partner";

    private FirebaseFirestore mInstance;

    @Inject
    public StoreRepoImpl(FirebaseFirestore instance) {
        mInstance = instance;
    }

    @Override
    public Single<List<Store>> getStore() {
        return getValue(mInstance.collection(STORE_COLLECTION), Store.class).toSingle();
    }

}
