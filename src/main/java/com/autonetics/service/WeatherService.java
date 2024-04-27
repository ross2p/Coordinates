package com.autonetics.service;

import com.autonetics.model.Coordinates;
import com.autonetics.model.weather.GeneralWeatherInfo;

import java.io.IOException;

public interface WeatherService {
    GeneralWeatherInfo getWeather(Coordinates coords) throws IOException;
}
