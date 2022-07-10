package com.watchList.controller;

import com.watchList.dto.AddToWatchlist;
import com.watchList.dto.CityWeatherDetails;
import com.watchList.dto.RemoveFromWatchlist;
import com.watchList.exception.AlreadyExistInWatchListException;
import com.watchList.exception.NoUserFoundException;
import com.watchList.service.IWatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/WatchList")
public class WatchListController {

    @Autowired
    private IWatchListService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public CityWeatherDetails addCity(@RequestBody AddToWatchlist request) throws AlreadyExistInWatchListException {
        return service.addToWatchList(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/remove/{cityname}/{userName}/{country}")
    public void removeCity(@PathVariable String cityname,@PathVariable String userName,@PathVariable String country) throws NoUserFoundException{
        RemoveFromWatchlist removeFromWatchlist = new RemoveFromWatchlist();
        removeFromWatchlist.setName(cityname);
        removeFromWatchlist.setUserName(userName);
        removeFromWatchlist.setCountry(country);
        service.remove(removeFromWatchlist);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cityList/{userName}")
        public List<CityWeatherDetails> cityWeatherDetailsList(@PathVariable String userName) {
        return service.list(userName);
    }

}
