package com.Bartek.Weather.controller;

import com.Bartek.Weather.api.ApiCaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@Controller
@RequestMapping("/weather")
public class MainController {
    private ApiCaller apiCaller = new ApiCaller();

    @GetMapping
    public String displayWeather() {
        return "main.html";
    }

    @PostMapping("/getWeather")
    public String getWeather(@RequestParam String city, @RequestParam String unitGroup, Model model) {
        try{
            Map weather = apiCaller.getWeather(city, unitGroup);
            List<String> formattedWeather = formatWeatherData(weather);
            model.addAttribute("weather", formattedWeather);
        }
        catch (HttpClientErrorException e) {
            model.addAttribute("errorMessage", "City has not been found!");
        }
        return "main.html";

        //List<String> formattedWeather = Arrays.asList("23.0", "New York", "50.0", "10.0");
    }

    private List<String> formatWeatherData(Map weatherData) {
        List<String> formattedWeatherData = new ArrayList<>();
        LinkedHashMap dayWeatherData = (LinkedHashMap) ((ArrayList<?>) weatherData.get("days")).getFirst();

        formattedWeatherData.add(String.valueOf(dayWeatherData.get("temp")));
        formattedWeatherData.add(String.valueOf(weatherData.get("resolvedAddress")));
        formattedWeatherData.add(String.valueOf(dayWeatherData.get("humidity")));
        formattedWeatherData.add(String.valueOf(dayWeatherData.get("windspeed")));

        return formattedWeatherData;
    }
}
