package com.watchList.dto;

import com.watchList.entity.Coordinate;
import com.watchList.entity.Temperature;
import com.watchList.entity.Weather;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


/*
    Response dto
 */

public class CityWeatherDetails {


    private long cityId;

    @NotBlank
    @Length(min = 2,max = 20)
    private String name;
    private Coordinate coord;
    private Weather weather;
    private Temperature temperature;
    private long visibility;
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getVisibility() {
        return visibility;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }
}
