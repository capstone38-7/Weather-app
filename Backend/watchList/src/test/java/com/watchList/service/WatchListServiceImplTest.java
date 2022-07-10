package com.watchList.service;

import com.watchList.dto.AddToWatchlist;
import com.watchList.dto.CityWeatherDetails;
import com.watchList.dto.RemoveFromWatchlist;
import com.watchList.entity.CityWeather;
import com.watchList.exception.AlreadyExistInWatchListException;
import com.watchList.exception.NoUserFoundException;
import com.watchList.repository.WatchListRepo;
import com.watchList.util.ToCityWeatherDetails;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class WatchListServiceImplTest {

    @Mock
    WatchListRepo watchListRepo;

    @Mock
    ToCityWeatherDetails util;

    @InjectMocks
    @Spy
    WatchListServiceImpl service;


    /*
    Scenario: when list is successfully fetched
    input : userName = "sample"
    Expectation : List Successfully fetched
    verifying : WatchListRepo#findByUserName(userName) is called once
     */
    @Test
    public void testList_1() {
        String userName = "sample";
        List<CityWeatherDetails> list = mock(List.class);
        List<CityWeather> weathers = mock(List.class);
        when(watchListRepo.findByUserName(userName)).thenReturn(weathers);
        when(util.toWeathersList(weathers)).thenReturn(list);
        List<CityWeatherDetails> result =  service.list(userName);
        assertSame(list,result);
        verify(watchListRepo).findByUserName(userName);
        verify(util).toWeathersList(weathers);
    }



    /*
    Scenario: When weather is added successfully
    input : AddToWatchlist data
    Expectation : weather is added, cityweatherDetails is returned
    verifying : 1) WatchListRepo#save(weather) is called once
                2) WatchListRepo#findByUserNameAndNameAndCountry(userName,name,country) is called once
     */

    @Test
    public void testAddToWatchList_1() throws Exception{

        AddToWatchlist data = new AddToWatchlist();
        data.setUserName("sample");
        data.setName("surat");
        data.setCountry("IN");
        String id = "sample-u-IN-u-surat";
        doReturn(id).when(service).generateId(data.getUserName(),data.getName(),data.getCountry());

        CityWeather cityWeather = mock(CityWeather.class);
        CityWeather savedCityWeather = mock(CityWeather.class);
        when(watchListRepo.save(cityWeather)).thenReturn(savedCityWeather);

        Optional<CityWeather> optional = Optional.empty();

        when(util.toCityWeather(data)).thenReturn(cityWeather);

        CityWeatherDetails details = mock(CityWeatherDetails.class);
        when(util.toCityWeatherDetails(savedCityWeather)).thenReturn(details);

        when(watchListRepo.findByUserNameAndNameAndCountry(data.getUserName(),data.getName(),data.getCountry())).thenReturn(optional);

        CityWeatherDetails result =  service.addToWatchList(data);
        assertSame(details,result);
        verify(watchListRepo).save(cityWeather);
    }

    /*
    Scenario: When weather is not added
    input : AddToWatchlist data
    Expectation : AlreadyExistInWatchListException is thrown
    verifying : 1) WatchListRepo#save(weather) is called once
                2) WatchListRepo#findByUserNameAndNameAndCountry(userName,name,country) is called once
     */

    @Test
    public void testAddToWatchList_2() throws Exception {

        AddToWatchlist data = new AddToWatchlist();
        data.setUserName("sample");
        data.setName("surat");
        data.setCountry("IN");
        CityWeather cityWeather = mock(CityWeather.class);
        when(util.toCityWeather(data)).thenReturn(cityWeather);

        Optional<CityWeather> optional = Optional.of(cityWeather);
        when(watchListRepo.findByUserNameAndNameAndCountry(data.getUserName(),data.getName(),data.getCountry())).thenReturn(optional);

        Executable executable = ()->{
            service.addToWatchList(data);
        };
        assertThrows(AlreadyExistInWatchListException.class,executable);
        verify(watchListRepo,never()).save(cityWeather);

    }

    /**
     * Scenario: when weather is removed successfully
     * input : RemoveFromWatchlist data
     * Expectation : weather is deleted
     * verifying : 1) WatchListRepo#delete(cityweather) is called once
     *             2) WatchListRepo#findByUserNameAndNameAndCountry(userName,name,country) is called once
     */

    @Test
    public void testRemove_1() throws Exception{

        RemoveFromWatchlist remove = new RemoveFromWatchlist();
        remove.setUserName("sample");
        remove.setName("surat");
        remove.setCountry("IN");

        CityWeather cityWeather = mock(CityWeather.class);

        Optional<CityWeather> optional = Optional.of(cityWeather);

        when(watchListRepo.findByUserNameAndNameAndCountry(remove.getUserName(),remove.getName(),remove.getCountry())).thenReturn(optional);

        service.remove(remove);

        verify(watchListRepo).delete(cityWeather);

    }

    /* Scenario: when no user found
       input : RemoveFromWatchlist data
       Expectation : NoUserFoundException is thrown
       verifying : 1) WatchListRepo#delete(cityweather) is called once
                   2) WatchListRepo#findByUserNameAndNameAndCountry(userName,name,country) is called once
     */

    @Test
    public void testRemove_2() throws Exception{

        RemoveFromWatchlist remove = new RemoveFromWatchlist();
        remove.setUserName("sample");
        remove.setName("surat");
        remove.setCountry("IN");

        CityWeather cityWeather = mock(CityWeather.class);

        Optional<CityWeather> optional = Optional.empty();

        when(watchListRepo.findByUserNameAndNameAndCountry(remove.getUserName(),remove.getName(),remove.getCountry())).thenReturn(optional);

        Executable executable = ()->{
            service.remove(remove);
        };
        assertThrows(NoUserFoundException.class,executable);
        verify(watchListRepo,never()).delete(cityWeather);

    }





}
