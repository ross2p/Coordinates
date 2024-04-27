package com.autonetics.service;

import com.autonetics.model.Coordinates;
import com.autonetics.model.DistanceInfo;

public interface DistanceService {
    DistanceInfo calculateDistance(Coordinates first, Coordinates second);
}
