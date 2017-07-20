package com.ikholopov.yamblz.weather.weathermobilization.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**One per Activity
 * Created by igor on 7/16/17.
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerView {

}
