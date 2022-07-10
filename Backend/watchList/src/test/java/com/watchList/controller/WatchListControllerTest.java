package com.watchList.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.watchList.dto.AddToWatchlist;
import com.watchList.dto.CityWeatherDetails;
import com.watchList.dto.RemoveFromWatchlist;
import com.watchList.entity.Coordinate;
import com.watchList.entity.Temperature;
import com.watchList.entity.Weather;
import com.watchList.exception.AlreadyExistInWatchListException;
import com.watchList.exception.NoUserFoundException;
import com.watchList.service.IWatchListService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(WatchListController.class)
class WatchListControllerTest  {

    private CityWeatherDetails cityWeatherDetails;
    private Temperature temperature;
    private Coordinate coord;
    private Weather weather;

    @MockBean
    IWatchListService service;

    @Autowired
    MockMvc mvc;

    @BeforeEach
    public void setup(){
         coord = new Coordinate();
        coord.setLongitude(23.432);
        coord.setLatitude(18.823);

        temperature = new Temperature();
        temperature.setPressure(100298);
        temperature.setTemp(39);
        temperature.setTempMin(37);
        temperature.setTempMax(40);
        temperature.setHumidity(102);
        temperature.setFeelsLike(40);

        weather = new Weather();
        weather.setDescription("broken clouds");
        weather.setMainInfo("Clouds");

        cityWeatherDetails = new CityWeatherDetails();
        cityWeatherDetails.setCityId(104924);
        cityWeatherDetails.setName("Vadodara");
        cityWeatherDetails.setWeather(weather);
        cityWeatherDetails.setCoord(coord);
        cityWeatherDetails.setTemperature(temperature);
        cityWeatherDetails.setVisibility(10002);
        cityWeatherDetails.setCountry("IN");

    }

    @AfterEach
    public void reset(){
        cityWeatherDetails = null;
    }

    /**
     * scenario: When userName is founded successfully
     * input : userName = "sample"
     * expectation: List of CityWeatherDetails is returned as response. status 200/OK
     */

    @Test
    public void testCityWeatherDetailsList_1() throws  Exception{
        String userName = "sample";

        List<CityWeatherDetails>  cityWeatherDetailsList =  new ArrayList<>();
        cityWeatherDetailsList.add(cityWeatherDetails);

        when(service.list(userName)).thenReturn(cityWeatherDetailsList);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cityWeatherDetailsList);
        String url = "/WatchList/cityList/"+userName;
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }




    /**
     * scenario: When city weather is added successfully
     * input : AddToWatchlist
     * expectation:  weather is added successfully. status 200/OK
     */
    @Test
    public void testAddCity_1() throws Exception {

        AddToWatchlist request = new AddToWatchlist();
        request.setUserName("sample");
        request.setName("Vadodara");
        request.setCountry("IN");
        request.setVisibility(10002);
        request.setCityId(104924);
        request.setWeather(weather);
        request.setTemperature(temperature);
        request.setCoord(coord);
        when(service.addToWatchList(request)).thenReturn(cityWeatherDetails);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);
        String jsonResponse = objectMapper.writeValueAsString(cityWeatherDetails);
        String url = "/WatchList/add";
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonResponse));
        verify(service).addToWatchList(request);

    }

    /**
     * scenario: When city weather is already exists
     * input : AddToWatchlist
     * expectation:  throws AlreadyExistInWatchListException , status 409/Found
     */

    @Test
    public void testAddCity_2() throws Exception {

        String message = "Already Added in Watch List";
        AlreadyExistInWatchListException  alreadyExist = new AlreadyExistInWatchListException(message);

        AddToWatchlist request = new AddToWatchlist();
        request.setUserName("sample");
        request.setName("Vadodara");
        request.setCountry("IN");
        request.setVisibility(10002);
        request.setCityId(104924);
        request.setWeather(weather);
        request.setTemperature(temperature);
        request.setCoord(coord);
        when(service.addToWatchList(request)).thenThrow(alreadyExist);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);
        String url = "/WatchList/add";
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                        .andExpect(status().isFound())
                                .andExpect(content().string(message));
        verify(service).addToWatchList(request);

    }

    /**
     * scenario: When weather is removed successfully
     * input : RemoveFromWatchlist
     * expectation:  Track is removed successfully. status 200/OK
     */
    @Test
    public void testRemove_1() throws Exception {

        RemoveFromWatchlist request = new RemoveFromWatchlist();
        request.setUserName("sample");
        request.setName("Vadodara");
        request.setCountry("IN");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);
        String url = "/WatchList/remove/"+request.getName()+"/"+request.getUserName()+"/"+request.getCountry();
        mvc.perform(delete(url).contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                        .andExpect(status().isOk());
        verify(service).remove(request);
    }

    /**
     * scenario: When track is not found
     * input : RemovedFavouriteRequest
     * expectation:  NoTrackFoundException. status 404/NOT_FOUND
     */
    @Test
    public void testRemove_2() throws Exception {
        RemoveFromWatchlist request = new RemoveFromWatchlist();
        request.setUserName("sample");
        request.setName("Vadodara");
        request.setCountry("IN");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);
        String url = "/WatchList/remove/"+request.getName()+"/"+request.getUserName()+"/"+request.getCountry();

        String message = "No user found";
        NoUserFoundException e = new NoUserFoundException(message);
        doThrow(e).when(service).remove(request);
        mvc.perform(delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isNotFound())
                .andExpect(content().string(message));
        verify(service).remove(request);
    }



}