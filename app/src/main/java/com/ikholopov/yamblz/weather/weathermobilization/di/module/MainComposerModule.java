package com.ikholopov.yamblz.weather.weathermobilization.di.module;

import com.ikholopov.yamblz.weather.weathermobilization.ui.MainViewComposer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by igor on 7/16/17.
 */

@Module
public class MainComposerModule {

    private MainViewComposer composer;

    public MainComposerModule(MainViewComposer composer) {
        this.composer = composer;
    }

    @Provides
    MainViewComposer provideMainViewComposer() {
        return composer;
    }
}
