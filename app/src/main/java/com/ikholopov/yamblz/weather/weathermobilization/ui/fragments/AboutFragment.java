package com.ikholopov.yamblz.weather.weathermobilization.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.ui.Named;


/**
 * {@link Named} {@link Fragment} with app information
 */
public class AboutFragment extends Fragment implements Named {

    public static final int FragmentNameId = R.string.nav_drawer_about;

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
    public int getNameId() {
        return FragmentNameId;
    }
}
