package com.watchList.service;

import com.watchList.dto.AddToWatchlist;
import com.watchList.dto.CityWeatherDetails;
import com.watchList.dto.RemoveFromWatchlist;
import com.watchList.entity.CityWeather;
import com.watchList.exception.AlreadyExistInWatchListException;
import com.watchList.exception.NoUserFoundException;
import com.watchList.repository.WatchListRepo;
import com.watchList.util.ToCityWeatherDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation For Watch List Service
 *
 */


@Service
public class WatchListServiceImpl implements IWatchListService{

    @Autowired
    private WatchListRepo watchListRepo;

    @Autowired
    private ToCityWeatherDetails toCityWeatherDetails;

    public String generateId(String userName,String name,String country) {
        return userName+"-u-"+country+"-u-"+name;
    }

    /**
     * Saves  City Weather Information For a user and returns WeatherDetails,
     * If User has already added the city weather information then AlreadyExistInWatchlistException is thrown
     * @param requestData AddToWatchList type
     * @return CityWeatherDetails
     * @throws AlreadyExistInWatchListException If City Already Exist For a User
     */

    @Override
    public CityWeatherDetails addToWatchList(AddToWatchlist requestData) throws AlreadyExistInWatchListException {

        CityWeather cityWeather = toCityWeatherDetails.toCityWeather(requestData);
        Optional<CityWeather> citylist =watchListRepo.findByUserNameAndNameAndCountry(requestData.getUserName(),requestData.getName(),requestData.getCountry());
        if(citylist.isPresent()){
            throw  new AlreadyExistInWatchListException("Already Added in Watch List");
        }
        String generatedId = generateId(requestData.getUserName(),requestData.getName(),requestData.getCountry());
        cityWeather.setId(generatedId);
        CityWeather savedWeather =  watchListRepo.save(cityWeather);
        return toCityWeatherDetails.toCityWeatherDetails(savedWeather);
    }

    /**
     * Remove the  weather from the favorite list
     *
     * @param requestData
     * @throws NoUserFoundException
     */

    @Override
    public void remove(RemoveFromWatchlist requestData) throws NoUserFoundException {
        Optional<CityWeather> weather = watchListRepo.findByUserNameAndNameAndCountry(requestData.getUserName(),requestData.getName(),requestData.getCountry());
        if(weather.isEmpty()){
            throw  new NoUserFoundException("User Not Found");
        }
        CityWeather cityWeather = weather.get();
        watchListRepo.delete(cityWeather);
    }

    /**
     * Getting the weather list of current user
     * @param userName
     * @return List<CityWeatherDetails>
     */
    @Override
    public List<CityWeatherDetails> list(String userName)  {
        List<CityWeather> weathers =  watchListRepo.findByUserName(userName);
        return toCityWeatherDetails.toWeathersList(weathers);
    }
}
