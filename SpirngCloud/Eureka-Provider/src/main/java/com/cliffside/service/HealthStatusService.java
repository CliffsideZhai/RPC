package com.cliffside.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.IllegalFormatCodePointException;

/**
 * @author cliffside
 * @date 2020-11-03 16:17
 */
@Service
public class HealthStatusService implements HealthIndicator {

    private Boolean status = true;

    public String getStatus() {
        return this.status.toString();
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public Health health() {
        if (status){
            return new Health.Builder().up().build();
        }
        return new Health.Builder().down().build();
    }
}
