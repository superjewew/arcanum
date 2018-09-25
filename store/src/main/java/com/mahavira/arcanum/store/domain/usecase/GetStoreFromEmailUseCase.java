package com.mahavira.arcanum.store.domain.usecase;

import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.arcanum.store.domain.repo.StoreRepository;
import com.mahavira.base.core.BaseUseCaseWithParam;
import io.reactivex.Single;
import javax.inject.Inject;

/**
 * Created by norman on 25/09/18.
 */

public class GetStoreFromEmailUseCase implements BaseUseCaseWithParam<String, Store> {

    private final StoreRepository mRepository;

    @Inject
    GetStoreFromEmailUseCase(StoreRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<Store> execute(final String param) {
        return mRepository.getStore(param);
    }
}
