package com.watchList.dto;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class RemoveFromWatchlist {


    @NotBlank
    @Length(min = 2,max = 20)
    private String userName;

    @NotBlank
    @Length(min = 2,max = 20)
    private String name;

    @NotBlank
    @Length(min = 2,max = 20)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveFromWatchlist that = (RemoveFromWatchlist) o;
        return Objects.equals(userName, that.userName) && Objects.equals(name, that.name) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, name, country);
    }
}
