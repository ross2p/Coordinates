package com.autonetics.service.impl;

import com.autonetics.model.Coordinates;
import com.autonetics.model.weather.GeneralWeatherInfo;
import com.autonetics.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${weather.api.key}")
    private String api_key;
    /**
     * Base URL for OpenWeatherMap API.
     */
    private final static String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    /**
     * Output language.
     */
    @Value("${weather.api.lang}")
    private final static String LANG = "en";
    /**
     * Temperature is available in Fahrenheit, Celsius and Kelvin units.
     */
    private final static String UNITS = "metric";

    @Override
    public GeneralWeatherInfo getWeather(Coordinates coords) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = String.format("%s?lat=%s&lon=%s&lang=%s&units=%s&appid=%s",
                BASE_URL,
                coords.getLatitude(),
                coords.getLongitude(),
                LANG,
                UNITS,
                api_key);

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
