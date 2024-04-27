package com.autonetics.service.impl;

import com.autonetics.model.Coord;
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
    private static final String API_KEY = "4e044df2a09db8e3c728b8de968aabc4";

    @Override
    public GeneralWeatherInfo getWeather(Coord coords) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s",
                coords.getLatitude(),
                coords.getLongitude(),
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
