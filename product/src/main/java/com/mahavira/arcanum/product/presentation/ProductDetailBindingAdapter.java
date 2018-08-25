package com.mahavira.arcanum.product.presentation;

import android.databinding.BindingAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

/**
 * Created by norman on 25/08/18.
 */

public class ProductDetailBindingAdapter {

    @BindingAdapter("gameComponent")
    public static void setGameComponent(LinearLayout view, List<String> components) {
        if(components != null) {
            for(String component : components) {
                TextView tv = new TextView(view.getContext());
                tv.setText(component);
                view.addView(tv);
            }
        }
    }

}
