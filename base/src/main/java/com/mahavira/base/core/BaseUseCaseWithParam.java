package com.mahavira.base.core;

import io.reactivex.Single;

/**
 * Created by norman on 25/01/18.
 */

public interface BaseUseCaseWithParam<P, R> {
    Single<R> execute(P param);
}
