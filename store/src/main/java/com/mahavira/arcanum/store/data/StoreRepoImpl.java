package com.mahavira.arcanum.store.data;

import android.support.annotation.NonNull;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.arcanum.store.domain.repo.StoreRepository;
import com.mahavira.base.core.BaseRepository;
import com.mahavira.base.entity.User;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by norman on 16/08/18.
 *
 */

public class StoreRepoImpl extends BaseRepository implements StoreRepository {

    private static final String USER_COLLECTION = "users";

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

    @Override
    public Completable setVisit(final String userEmail, final String storeEmail) {
        return getUser(userEmail).zipWith(getStore(storeEmail), this::updateUserVisitAt)
                .flatMapCompletable(user ->
                        setValue(mInstance.collection(USER_COLLECTION).document(user.getEmail()), user));
    }

    @NonNull
    private User updateUserVisitAt(final List<User> users, final List<Store> stores) {
        User user = users.get(0);
        Store store;
        if(stores != null && stores.size() != 0) {
            store = stores.get(0);
            user.setPlayingAt(store.getName());
            user.addRecentStores(store.getEmail());
        } else {
            user.setPlayingAt("");
        }
        return user;
    }

    private Single<List<User>> getUser(String email) {
        return query(mInstance.collection(USER_COLLECTION), "email", email, User.class).toSingle();
    }

    private Single<List<Store>> getStore(String email) {
        return query(mInstance.collection(STORE_COLLECTION), "email", email, Store.class).toSingle();
    }
}
