package com.ikholopov.yamblz.weather.weathermobilization.model.services;

import org.threeten.bp.Duration;

/**
 * Responsible for starting a service
 * Created by igor on 7/21/17.
 */

public interface UpdateServiceController {
    void enableService(Duration updateInterval);
    void disableService();
}
