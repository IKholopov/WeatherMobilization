package com.ikholopov.yamblz.weather.weathermobilization.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Base fragment, containing utility functional for work with {@link ButterKnife} framework
 *
 * Bind views on ids (set with annotation), save bindings and unbind its when view destroyed
 *
 * Created by turist on 02.08.2017.
 */
public class KnifeFragment extends Fragment {

    private Unbinder unbinder;

    /**
     * Need call from {{@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}}
     */
    protected View bind(View view) {
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}