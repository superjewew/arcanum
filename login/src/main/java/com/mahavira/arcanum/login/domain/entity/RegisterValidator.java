package com.mahavira.arcanum.login.domain.entity;

import java.util.regex.Pattern;
import javax.annotation.Nonnull;

/**
 * Created by norman on 20/08/18.
 *
 * Used for validating registration form input.
 */

public class RegisterValidator {

    public String mNameError = "";

    public String mEmailError = "";

    public String mPasswordError = "";

    public String mConfirmError = "";

    private User mUser;

    private Pass mSecret;

    /**
     * Set input to be validated. User and pass object cannot be null as we need to inspect each field from each
     * objects.
     *
     * @param user   user parameter to be validated
     * @param secret pass parameter to be validated
     */
    public void setInput(@Nonnull User user, @Nonnull Pass secret) {
        mUser = user;
        mSecret = secret;
    }

    /**
     * Check whether input that has been inserted via setInput method is valid or not. When input failed one or more
     * validation, it will set one or more error messages field. As the error message is a public field, another class
     * can simply take the value from the variables.
     *
     * @return is input valid or not.
     */
    public boolean isValid() {
        resetError();

        boolean valid = true;

        if (mUser == null || mSecret == null) {
            return false;
        }

        if (mUser.getName().equals("")) {
            mNameError = "Name cannot be empty";
            valid = false;
        }

        if (mUser.getEmail().equals("")) {
            mEmailError = "Email cannot be empty";
            valid = false;
        }

        if (isEmailPatternNotValid(mUser.getEmail())) {
            mEmailError = "Must be valid email address";
            valid = false;
        }

        if (mSecret.getPass().equals("")) {
            mPasswordError = "Password cannot be empty";
            valid = false;
        }

        if (!mSecret.getPass().equals(mSecret.getConfirm())) {
            mConfirmError = "Must be same with password";
            valid = false;
        }

        return valid;
    }

    private void resetError() {
        mNameError = "";
        mEmailError = "";
        mPasswordError = "";
        mConfirmError = "";
    }

    private boolean isEmailPatternNotValid(String email) {
        return !isEmailPatternValid(email);
    }

    private boolean isEmailPatternValid(String email) {
        Pattern emailAddressPattern = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );

        return emailAddressPattern.matcher(email).matches();
    }

}
