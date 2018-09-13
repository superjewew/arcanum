package com.mahavira.arcanum.store.domain.usecase;

import com.mahavira.arcanum.store.domain.entity.SetVisitParam;
import com.mahavira.arcanum.store.domain.repo.StoreRepository;
import com.mahavira.base.core.CompletableUseCase;
import io.reactivex.Completable;
import javax.inject.Inject;

/**
 * Created by norman on 05/09/18.
 *
 */

public class SetVisitUseCase implements CompletableUseCase<SetVisitParam> {

    private StoreRepository mRepository;

    @Inject
    public SetVisitUseCase(StoreRepository repository) {
        mRepository = repository;
    }

    @Override
    public Completable execute(final SetVisitParam param) throws Exception {
        return mRepository.setVisit(param.getUserEmail(), param.getStoreEmail());
    }
}
