package com.autonetics.service.impl;

import com.autonetics.model.Coordinates;
import com.autonetics.model.DistanceInfo;
import com.autonetics.service.DistanceService;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceImpl implements DistanceService {
    private final static double EARTH_RADIUS = 6371;
    @Override
    public DistanceInfo calculateDistance(Coordinates from, Coordinates to) {

            double dLat = Math.toRadians(to.getLatitude() - from.getLatitude());
            double dLon = Math.toRadians(to.getLongitude() - from.getLongitude());

            double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                    Math.cos(Math.toRadians(from.getLatitude())) * Math.cos(Math.toRadians(to.getLongitude())) *
                            Math.sin(dLon/2) * Math.sin(dLon/2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return new DistanceInfo(EARTH_RADIUS * c);
    }
}
