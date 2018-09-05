package com.mahavira.arcanum.store.domain.repo;

import com.mahavira.arcanum.store.domain.entity.Store;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by norman on 16/08/18.
 *
 */

public interface StoreRepository {

    Single<List<Store>> getStore();
    Completable setVisit(String user, String store);
}
