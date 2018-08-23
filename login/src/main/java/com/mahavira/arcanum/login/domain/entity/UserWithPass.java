package com.mahavira.arcanum.login.domain.entity;

import com.mahavira.base.entity.User;

/**
 * Created by norman on 20/08/18.
 *
 */

public class UserWithPass {

    private User mUser;
    private String pass;

    public UserWithPass(User user, String pass) {
        setUser(user);
        setPass(pass);
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(final User user) {
        mUser = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(final String pass) {
        this.pass = pass;
    }
}
