package com.mahavira.base.core;

import io.reactivex.Completable;

public interface CompletableUseCase<P> {
    Completable execute(P param) throws Exception;
}
