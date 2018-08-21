package com.mahavira.arcanum.login.presentation.feature.register;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;

/**
 * Created by norman on 20/08/18.
 *
 */

public class RegisterBindingAdapters {

    @BindingAdapter("app:errorText")
    public static void setErrorText(TextInputLayout view, String message) {
        view.setError(message);
    }

}
