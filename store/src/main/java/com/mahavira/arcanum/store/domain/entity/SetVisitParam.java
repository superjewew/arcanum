package com.mahavira.arcanum.store.domain.entity;

/**
 * Created by norman on 05/09/18.
 *
 */

public class SetVisitParam {

    private final String userEmail;
    private final String storeEmail;

    public SetVisitParam(final String user, final String store) {
        userEmail = user;
        storeEmail = store;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

}
