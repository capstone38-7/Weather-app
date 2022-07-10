package com.watchList.util;

import com.watchList.dto.AddToWatchlist;
import com.watchList.dto.CityWeatherDetails;
import com.watchList.entity.CityWeather;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ToCityWeatherDetails {

    public CityWeatherDetails toCityWeatherDetails(CityWeather cityWeather){
        CityWeatherDetails cityWeatherDetails = new CityWeatherDetails();
        cityWeatherDetails.setName(cityWeather.getName());
        cityWeatherDetails.setCityId(cityWeather.getCityId());
        cityWeatherDetails.setCoord(cityWeather.getCoord());
        cityWeatherDetails.setTemperature(cityWeather.getTemperature());
        cityWeatherDetails.setVisibility(cityWeather.getVisibility());
        cityWeatherDetails.setWeather(cityWeather.getWeather());
        cityWeatherDetails.setCountry(cityWeather.getCountry());
        return  cityWeatherDetails;
    }

    public List<CityWeatherDetails> toWeathersList(List<CityWeather> weathers){
        List<CityWeatherDetails> weatherDetails = new ArrayList<>();
        for(CityWeather city:weathers){
            weatherDetails.add(toCityWeatherDetails(city));
        }
        return weatherDetails;
    }

    public CityWeather toCityWeather(AddToWatchlist request){
        CityWeather cityWeather = new CityWeather();
        cityWeather.setCityId(request.getCityId());
        cityWeather.setWeather(request.getWeather());
        cityWeather.setCoord(request.getCoord());
        cityWeather.setName(request.getName());
        cityWeather.setTemperature(request.getTemperature());
        cityWeather.setVisibility(request.getVisibility());
        cityWeather.setUserName(request.getUserName());
        cityWeather.setCountry(request.getCountry());
        return cityWeather;
    }



}
