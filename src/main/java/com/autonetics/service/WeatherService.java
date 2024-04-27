package com.autonetics.service;

import com.autonetics.model.Coord;
import com.autonetics.model.GeneralWeatherInfo;

import java.io.IOException;

public interface WeatherService {
    GeneralWeatherInfo getWeather(Coord coords) throws IOException;
}
