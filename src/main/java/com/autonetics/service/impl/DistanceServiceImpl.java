package com.autonetics.service.impl;

import com.autonetics.model.Coord;
import com.autonetics.model.DistanceInfo;
import com.autonetics.service.DistanceService;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceImpl implements DistanceService {
    @Override
    public DistanceInfo calculateDistance(Coord from, Coord to) {
            double earthRadius = 6371;

            double dLat = Math.toRadians(to.getLatitude() - from.getLatitude());
            double dLon = Math.toRadians(to.getLongitude() - from.getLongitude());

            double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                    Math.cos(Math.toRadians(from.getLatitude())) * Math.cos(Math.toRadians(to.getLongitude())) *
                            Math.sin(dLon/2) * Math.sin(dLon/2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        DistanceInfo distanceInfo = new DistanceInfo(earthRadius * c);
        return distanceInfo;

    }
}
