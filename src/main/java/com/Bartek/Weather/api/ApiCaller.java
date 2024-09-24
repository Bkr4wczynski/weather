package com.Bartek.Weather.api;

import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.Map;

public class ApiCaller {
    private RestClient restClient = RestClient.create();
    private static String API_KEY = "4J5DTX4MSX4C9V8DTLNTZFPJD";

    private URI createUri(String city, String unitGroup) {
        return URI.create("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                + city + "/last1days?unitGroup=" + unitGroup + "&key=" + API_KEY + "&contentType=json");
    }

    public Map getWeather(String city, String unitGroup) {
        return restClient.get()
                .uri(createUri(city, unitGroup))
                .retrieve()
                .body(Map.class);
    }

}
