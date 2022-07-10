package com.watchList.service;

import com.watchList.dto.AddToWatchlist;
import com.watchList.dto.CityWeatherDetails;
import com.watchList.dto.RemoveFromWatchlist;
import com.watchList.exception.AlreadyExistInWatchListException;
import com.watchList.exception.NoUserFoundException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
public interface IWatchListService {
    CityWeatherDetails addToWatchList(@Valid AddToWatchlist requestData) throws AlreadyExistInWatchListException;
    void remove(@Valid RemoveFromWatchlist requestData) throws NoUserFoundException;
    List<CityWeatherDetails> list(@NotBlank String userName) ;
}
