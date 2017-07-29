package com.ikholopov.yamblz.weather.weathermobilization.presenter;

/**
 * Responsible for starting a service
 * Created by igor on 7/21/17.
 */

public interface UpdateServiceController {
    void enableService(int updateInterval);
    void disableService();
}
