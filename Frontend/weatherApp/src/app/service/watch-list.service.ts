import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CityWeather } from '../model/cityWeather/CityWeather';
import { baseServerUrl } from '../model/fakedata';

@Injectable({
  providedIn: 'root'
})
export class WatchListService {

  constructor(private http:HttpClient) {
    
  }

  addWeather(data:CityWeather){
    const temp ={userName:localStorage.getItem('username'),...data};
    return this.http.post<CityWeather>(`${baseServerUrl}/WatchList/add`,temp);
  }

  getWeather():Observable<CityWeather[]>{
    const userName:string = localStorage.getItem('username') 
    return this.http.get<CityWeather[]>(`${baseServerUrl}/WatchList/cityList/${userName}`);
  }

  removeWeather(cityname:string,userName:string,country:string){
    return this.http.delete(`${baseServerUrl}/WatchList/remove/${cityname}/${userName}/${country}`);
  }

  search(city:string,country:string){
    return this.http.get(`http://api.openweathermap.org/data/2.5/weather?q=${city},${country}&APPID=69b9d6824b74d777140e366d6eee82da`);
  }
}
