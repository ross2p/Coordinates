package com.autonetics.service;

import com.autonetics.model.Coord;
import com.autonetics.model.DistanceInfo;

public interface DistanceService {
    DistanceInfo calculateDistance(Coord first, Coord second);
}
