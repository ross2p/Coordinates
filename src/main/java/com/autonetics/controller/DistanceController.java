package com.autonetics.controller;


import com.autonetics.model.Coord;
import com.autonetics.model.DistanceInfo;
import com.autonetics.service.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/distance")
@RequiredArgsConstructor
public class DistanceController {
    private final DistanceService distanceService;
    @RequestMapping
    public DistanceInfo getDistance(@RequestBody List<Coord> coords) {
        return distanceService.calculateDistance(coords.get(0), coords.get(1));
    }
}
