package com.mahavira.arcanum.friends.domain.entity;

import android.util.Patterns;

/**
 * Created by norman on 06/09/18.
 *
 */

public class AddFriendParam {

    private String userEmail;
    private String friendToBeAdded;
    private boolean isEmail;

    public AddFriendParam(String userEmail, String friend) {
        this.userEmail = userEmail;
        this.friendToBeAdded = friend;
        isEmail = Patterns.EMAIL_ADDRESS.matcher(friend).matches();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getFriendToBeAdded() {
        return friendToBeAdded;
    }

    public boolean isEmail() {
        return isEmail;
    }
}
