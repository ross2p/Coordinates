package com.autonetics.service;

import com.autonetics.model.Goods;
import com.autonetics.model.weather.GeneralWeatherInfo;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public interface ChatGPTService {
    Goods sendPriceAIMsg(Goods goods, GeneralWeatherInfo weatherInfo);
}
