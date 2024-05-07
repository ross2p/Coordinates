package com.autonetics.controller;

import com.autonetics.mappers.GoodsMapper;
import com.autonetics.model.Coordinates;
import com.autonetics.model.Goods;
import com.autonetics.request.RequestObject;
import com.autonetics.service.ChatGPTService;
import com.autonetics.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/chatGPT")
@RequiredArgsConstructor
public class ChatGPTController {
    private final ChatGPTService chatGPTService;
    private final WeatherService weatherService;
//    private final GoodsMapper goodsMapper;

    @PostMapping
    public Goods getChatGPT(@RequestBody RequestObject requestObject) throws IOException {
        Goods goods = requestObject.getGoods();
        Coordinates coordinates = requestObject.getCoordinates();
        return chatGPTService.sendPriceAIMsg(goods, weatherService.getWeather(coordinates));
    }
}
