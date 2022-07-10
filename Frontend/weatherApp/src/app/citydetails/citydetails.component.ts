import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CityWeather } from '../model/cityWeather/CityWeather';
import { WatchListService } from '../service/watch-list.service';
import { WeatherUtil } from '../util/weather.util';

@Component({
  selector: 'city-details',
  templateUrl: './citydetails.component.html',
  styleUrls: ['./citydetails.component.css']
})
export class CityWeatherComponent implements OnInit {

  util: WeatherUtil = new WeatherUtil();
  city: string | undefined;
  country: string | undefined;
  cityWeather: CityWeather;
  weekday = new Date().getDay();
  flag = false;
  alreadyflag= false;

  constructor(route: ActivatedRoute, private service: WatchListService, private router: Router) {
    const observer = {
      next: (map: ParamMap) => {
        this.city = map.get("city");
        this.country = map.get("country");
        this.searchResult();
      }
    }
    const observable: Observable<ParamMap> = route.paramMap;
    observable.subscribe(observer);
  }

  ngOnInit(): void {
  }

  searchResult() {
    this.service.search(this.city, this.country).subscribe(data => {
      this.cityWeather = this.util.toCityWeather(data);
      console.log(this.cityWeather);
    })
  }



  addWeatherToWatchListdb() {

    const obser = {
      next: (data: CityWeather) => {
          console.log(data);
          this.flag = true;
          setInterval(() => {
          this.flag = false
            }, 2000);
      },
      error:(err:Error)=>{
        this.alreadyflag = true;
          setInterval(() => {
          this.alreadyflag = false
            }, 2000);
      }
    }


    const observer =  this.service.addWeather(this.cityWeather);
    observer.subscribe(obser);
  }

}
