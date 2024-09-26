package com.Bartek.Weather.api;

import org.springframework.web.client.RestClient;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ApiCaller {
    private RestClient restClient = RestClient.create();
    private static String API_KEY = "My private api key";

    private URI createUri(String city, String unitGroup) {
        return URI.create("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                + URLEncoder.encode(city, StandardCharsets.UTF_8) + "/last1days?unitGroup=" + unitGroup + "&key=" + API_KEY + "&contentType=json");
    }

    public Map getWeather(String city, String unitGroup) {
        return restClient.get()
                .uri(createUri(city, unitGroup))
                .retrieve()
                .body(Map.class);
    }

}
