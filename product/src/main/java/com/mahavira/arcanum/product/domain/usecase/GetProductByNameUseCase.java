package com.mahavira.arcanum.product.domain.usecase;


import com.mahavira.arcanum.product.domain.entity.Boardgame;
import com.mahavira.arcanum.product.domain.repo.ProductRepository;
import com.mahavira.base.core.BaseUseCaseWithParam;
import io.reactivex.Single;
import javax.inject.Inject;

/**
 * Created by norman on 17/07/18.
 *
 */

public class GetProductByNameUseCase implements BaseUseCaseWithParam<String, Boardgame> {

    private ProductRepository mRepository;

    @Inject
    public GetProductByNameUseCase(ProductRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<Boardgame> execute(String param) {
        return mRepository.getProductByName(param);
    }
}
