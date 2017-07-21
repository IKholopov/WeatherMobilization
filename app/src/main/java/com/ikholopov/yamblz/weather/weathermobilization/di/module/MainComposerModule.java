package com.ikholopov.yamblz.weather.weathermobilization.di.module;

import com.ikholopov.yamblz.weather.weathermobilization.ui.MainViewComposer;
import com.ikholopov.yamblz.weather.weathermobilization.ui.MainViewComposerImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Provides Composer for MainActivity
 * Created by igor on 7/16/17.
 */

@Module
public class MainComposerModule {

    private MainViewComposer composer;

    public MainComposerModule() {
        this.composer = new MainViewComposerImpl();
    }

    @Provides
    MainViewComposer provideMainViewComposer() {
        return composer;
    }
}
