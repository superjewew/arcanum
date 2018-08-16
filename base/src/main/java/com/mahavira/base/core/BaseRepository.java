package com.mahavira.base.core;

import android.support.annotation.NonNull;
import com.google.firebase.firestore.CollectionReference;
import io.reactivex.Maybe;
import java.util.List;

/**
 * Created by norman on 16/08/18.
 */

public class BaseRepository {

    @NonNull
    protected <T> Maybe<List<T>> getValue(@NonNull final CollectionReference ref, Class<T> clazz) {
        return Maybe.create(
                e -> ref.get()
                        .addOnCompleteListener(task -> e.onSuccess(task.getResult().toObjects(clazz)))
                        .addOnFailureListener(e::onError));
    }

}
