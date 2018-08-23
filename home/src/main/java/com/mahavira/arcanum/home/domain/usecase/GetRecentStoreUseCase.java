package com.mahavira.arcanum.home.domain.usecase;

import com.mahavira.arcanum.home.domain.repo.HomeRepository;
import com.mahavira.base.core.BaseUseCaseWithParam;
import com.mahavira.base.entity.User;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by norman on 23/08/18.
 *
 */

public class GetRecentStoreUseCase implements BaseUseCaseWithParam<String, List<String>> {

    private HomeRepository mRepository;

    @Inject
    GetRecentStoreUseCase(HomeRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<List<String>> execute(final String param) {
        return mRepository.getUserDetail(param).flatMap(this::getRecentStore);
    }

    private Single<List<String>> getRecentStore(final User user) {
        List<String> result = user.getRecentStores();

        return Single.just(result);
    }
}
