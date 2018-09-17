package com.mahavira.arcanum.product.presentation;

import android.databinding.BindingAdapter;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * Created by norman on 17/09/18.
 */

public class ProductBindingAdapter {

    @BindingAdapter("tutorialUrl")
    public static void setTutorialUrl(TextView view, String url) {
        view.setText(Html.fromHtml("<a href=\"" + url + "\">Go to YouTube</a> "));
        view.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
