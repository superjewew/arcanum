package com.mahavira.arcanum.login.domain.entity;

/**
 * Created by norman on 20/08/18.
 *
 */

public class Pass {
    private String pass = "";
    private String confirm = "";

    public String getPass() {
        return pass;
    }

    public void setPass(final String pass) {
        this.pass = pass;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(final String confirm) {
        this.confirm = confirm;
    }
}
