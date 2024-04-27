package com.autonetics.service.impl;

import com.autonetics.model.Coordinates;
import com.autonetics.model.weather.GeneralWeatherInfo;
import com.autonetics.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Service
public class WeatherServiceImpl implements WeatherService {
    /**
     * API key for OpenWeatherMap.
     * <a href="https://home.openweathermap.org/api_keys">...</a>"
     */
    private static final String API_KEY = "4e044df2a09db8e3c728b8de968aabc4";
    /**
     * Base URL for OpenWeatherMap API.
     */
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    /**
     * Output language.
     */
    private static final String LANG = "en";
    /**
     * Temperature is available in Fahrenheit, Celsius and Kelvin units.
     */
    private static final String UNITS = "metric";

    @Override
    public GeneralWeatherInfo getWeather(Coordinates coords) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = String.format("%s?lat=%s&lon=%s&lang=%s&units=%s&appid=%s",
                BASE_URL,
                coords.getLatitude(),
                coords.getLongitude(),
                LANG,
                UNITS,
                API_KEY);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(responseBody, GeneralWeatherInfo.class);
    }
}
