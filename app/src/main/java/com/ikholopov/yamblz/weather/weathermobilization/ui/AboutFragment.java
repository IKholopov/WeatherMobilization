package com.ikholopov.yamblz.weather.weathermobilization.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikholopov.yamblz.weather.weathermobilization.R;


/**
 * {@link Named} {@link Fragment} с информацией о приложении.
 */
public class AboutFragment extends Fragment implements Named {

    public static final String mFragmentName = "About";

    public AboutFragment() {
    }

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public String getName() {
        return mFragmentName;
    }
}
