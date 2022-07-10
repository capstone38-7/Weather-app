package com.watchList.repository;

import com.watchList.entity.CityWeather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchListRepo extends MongoRepository<CityWeather,String> {

    Optional<CityWeather>  findByUserNameAndNameAndCountry(String userName, String name,String country);
    List<CityWeather> findByUserName(String userName);
}
