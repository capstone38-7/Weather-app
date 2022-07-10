package com.watchList.dto;

import com.watchList.entity.Coordinate;
import com.watchList.entity.Temperature;
import com.watchList.entity.Weather;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;


/**
 * Request Data for adding a City Weather For a User
 */

public class AddToWatchlist {

    @NotBlank
    @Length(min =   2,max = 20)
    private String userName;

    private long cityId;

    @NotBlank
    @Length(min =   2,max = 20)
    private String name;

    @NotNull
    private Coordinate coord;
    @NotNull
    private Weather weather;
    @NotNull
    private Temperature temperature;
    @Min(1)
    private long visibility;

    @NotBlank
    @Length(min=2,max=20)
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddToWatchlist that = (AddToWatchlist) o;
        return Objects.equals(userName, that.userName) && Objects.equals(name, that.name) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, name, country);
    }
}
