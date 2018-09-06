package com.mahavira.base.exception;

/**
 * Created by norman on 06/09/18.
 *
 */

public class FriendAlreadyAddedException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Friend already added";
    }
}
