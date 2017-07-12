package com.ikholopov.yamblz.weather.weathermobilization;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by igor on 7/9/17.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
