package com.mahavira.arcanum.store.domain.usecase;

import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.arcanum.store.domain.repo.StoreRepository;
import com.mahavira.base.core.BaseUseCase;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by norman on 15/07/18.
 *
 */

public class GetPartnersUseCase implements BaseUseCase<List<Store>> {

    private StoreRepository mRepository;

    @Inject
    public GetPartnersUseCase(StoreRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<List<Store>> execute() {
        return mRepository.getStore();
    }
}
