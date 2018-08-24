package com.mahavira.base.core;

import android.support.annotation.NonNull;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import java.util.List;

/**
 * Created by norman on 16/08/18.
 *
 */

public class BaseRepository {

    @NonNull
    protected <T> Maybe<List<T>> getValue(@NonNull final CollectionReference ref, Class<T> clazz) {
        return Maybe.create(
                e -> ref.get()
                        .addOnCompleteListener(task -> e.onSuccess(task.getResult().toObjects(clazz)))
                        .addOnFailureListener(e::onError));
    }

    @NonNull
    protected <T> Maybe<T> getValue(@NonNull final DocumentReference ref, Class<T> clazz) {
        return Maybe.create(
                e -> ref.get()
                        .addOnCompleteListener(task -> e.onSuccess(task.getResult().toObject(clazz)))
                        .addOnFailureListener(e::onError));
    }

    @NonNull
    protected Completable setValue(@NonNull final DocumentReference ref, final Object value) {
        return Completable.create(
                e -> ref.set(value)
                        .addOnSuccessListener(documentReference -> e.onComplete())
                        .addOnFailureListener(e::onError));
    }

}