package com.autonetics.service.impl;

import com.autonetics.config.ChatGPTPrompt;
import com.autonetics.model.Coordinates;
import com.autonetics.model.Goods;
import com.autonetics.model.weather.GeneralWeatherInfo;
import com.autonetics.service.ChatGPTService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Service
public class ChatGPTSeviceImpl implements ChatGPTService {
    private OpenAiService openAiService;
//    @Autowired
//    private GoodsMapper goodsMapper;

    @Value("${openai.api.key}")
    private String apiKey;

    private static final int apiTimeout = 10;
    private static final String GPT_MODEL = "gpt-3.5-turbo";

    @PostConstruct
    public void initGptService() {
        System.out.println(apiKey);
        openAiService = new OpenAiService(apiKey,
                Duration.ofSeconds(apiTimeout));
    }

    private String sendMsg(String systemMSG, String userMSG) {
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model(GPT_MODEL)
                .temperature(1.0)
                .messages(
                        List.of(
                                new ChatMessage("system", systemMSG),
                                new ChatMessage("user", userMSG)))
                .build();

        StringBuilder builder = new StringBuilder();

        openAiService.createChatCompletion(chatCompletionRequest).getChoices().forEach(choice ->
                builder.append(choice.getMessage().getContent()
                ));
        return builder.toString();
    }

    public Goods sendPriceAIMsg(Goods goods, GeneralWeatherInfo generalWeatherInfo) {
//        GoodsDTO goodsDTO =  goodsMapper.toDTO(goods);
        String userMSg = "My goods is "+ goods + " and the weather is " + generalWeatherInfo.toString();
        String response = sendMsg(ChatGPTPrompt.PRICE_AI_MSG, userMSg);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            double priceAI = jsonNode.get("priceAI").asDouble();
            goods.setPriceAI(priceAI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goods;
    }
    public static void main(String[] args) throws IOException {
        WeatherServiceImpl weatherService = new WeatherServiceImpl();
        Goods goods = new Goods();
        goods.setName("Beer");
        goods.setPriceIn(100);
        goods.setPriceOut(80);
        GeneralWeatherInfo generalWeatherInfo = weatherService.getWeather(new Coordinates(50.4547, 30.5238));


        ChatGPTSeviceImpl chatGPTSevice = new ChatGPTSeviceImpl();
        chatGPTSevice.apiKey = "sk-proj-SHyWg35xyKiAKKqyJ48wT3BlbkFJuI8LHq1FPURtnk3IEYCW";
        chatGPTSevice.initGptService();
        System.out.println(chatGPTSevice.sendPriceAIMsg(goods, generalWeatherInfo));
    }
}
