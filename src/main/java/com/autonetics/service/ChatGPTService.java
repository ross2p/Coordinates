package com.autonetics.service;

import com.autonetics.model.Goods;
import com.autonetics.model.weather.GeneralWeatherInfo;

public interface ChatGPTService {
    Goods sendPriceAIMsg(Goods goods, GeneralWeatherInfo weatherInfo);
}
