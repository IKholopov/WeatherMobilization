package com.ikholopov.yamblz.weather.weathermobilization.ui;

import android.support.annotation.NonNull;

/**
 * Created by igor on 7/15/17.
 */

public interface MainViewComposer {

    //Bind activity to be composed
    void bind(@NonNull NavigatableActivity activity);

    //Activity set up
    void setUpView(boolean initial);

    //Navigation handling
    void handleNavigation(int navId);

    //On back button
    void onBackPressed();
}
