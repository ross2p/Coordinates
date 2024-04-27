package com.autonetics.controller;

import com.autonetics.model.Coord;
import com.autonetics.model.weather.GeneralWeatherInfo;
import com.autonetics.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherControler {
    private final WeatherService weatherService;

    @RequestMapping
    public GeneralWeatherInfo getWeather(@RequestBody Coord coords) throws IOException {
        return weatherService.getWeather(coords);
    }
}
