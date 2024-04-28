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
    /**
     * API key for OpenWeatherMap.
     * <a href="https://home.openweathermap.org/api_keys">...</a>"
     */
    @Value("${weather.api.key}")
    private String api_key;
    /**
     * Base URL for OpenWeatherMap API.
     */
    @Value("${weather.api.url}")
    private String baseUrl;
    /**
     * Output language.
     */
    @Value("${weather.api.lang}")
    private String lang;
    /**
     * Temperature is available in Fahrenheit, Celsius and Kelvin units.
     */
    @Value("${weather.api.units}")
    private String units;

    @Override
    public GeneralWeatherInfo getWeather(Coordinates coords) throws IOException {
        System.out.println("baseUrl: " + baseUrl);
        OkHttpClient client = new OkHttpClient();

        String url = String.format("%s?lat=%s&lon=%s&lang=%s&units=%s&appid=%s",
                baseUrl,
                coords.getLatitude(),
                coords.getLongitude(),
                lang,
                units,
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
