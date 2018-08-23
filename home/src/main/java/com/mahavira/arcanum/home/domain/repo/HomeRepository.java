package com.mahavira.arcanum.home.domain.repo;

import com.mahavira.base.entity.User;
import io.reactivex.Single;

/**
 * Created by norman on 23/08/18.
 *
 */

public interface HomeRepository {

    Single<User> getUserDetail(String email);
}
