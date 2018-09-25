package com.mahavira.arcanum.home.domain.usecase;

import com.mahavira.arcanum.home.domain.repo.HomeRepository;
import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.base.core.BaseUseCaseWithParam;
import com.mahavira.base.entity.User;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by norman on 23/08/18.
 *
 */

public class GetRecentStoreUseCase implements BaseUseCaseWithParam<String, List<Store>> {

    private HomeRepository mRepository;

    @Inject
    GetRecentStoreUseCase(HomeRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<List<Store>> execute(final String param) {
        return mRepository.getUserDetail(param).zipWith(mRepository.getAllStore(),
                this::getRecentlyVisitedStoreFromUser);
    }

    private List<Store> getRecentlyVisitedStoreFromUser(User user, List<Store> stores) {
        List<String> recentStoreEmails = user.getRecentStores();
        List<Store> toBeRemoved = new ArrayList<>();
        List<Store> res = new ArrayList<>();
        res.addAll(stores);
        for (Store store : res) {
            if(!recentStoreEmails.contains(store.getEmail())) {
                toBeRemoved.add(store);
            }
        }

        res.removeAll(toBeRemoved);
        return res;
    }
}
