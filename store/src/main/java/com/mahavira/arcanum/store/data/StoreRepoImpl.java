package com.mahavira.arcanum.store.data;

import android.support.annotation.NonNull;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.arcanum.store.domain.repo.StoreRepository;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by norman on 16/08/18.
 */

public class StoreRepoImpl implements StoreRepository {

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

    @NonNull
    private <T> Maybe<List<T>> getValue(@NonNull final CollectionReference ref, Class<T> clazz) {
        return Maybe.create(
                e -> ref.get()
                        .addOnCompleteListener(task -> e.onSuccess(task.getResult().toObjects(clazz)))
                        .addOnFailureListener(e::onError));
    }
}
