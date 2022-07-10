import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { CityWeather } from '../model/cityWeather/CityWeather';

import { WatchListService } from '../service/watch-list.service';

@Component({
  selector: 'app-list-cities-weather',
  templateUrl: './list-cities-weather.component.html',
  styleUrls: ['./list-cities-weather.component.css']
})
export class ListCitiesWeatherComponent implements OnInit {

  citiesWeather: CityWeather[];
  flag = false;
  path: string = `city-weather/${localStorage.getItem('currentCity')}/null`;

  constructor(private service: WatchListService, private router: Router) { }

  ngOnInit(): void {
    this.getWeather()
  }


  getWeather() {
    this.service.getWeather().subscribe((data: CityWeather[]) => {
      this.citiesWeather = data;
      console.log(data)
    });
  }

  onDelete(cityname: string, country: string) {
    const userName = localStorage.getItem('username');

    const obser = {
      next: (data: CityWeather[]) => {
        this.getWeather();
        this.flag = true;
        setInterval(() => {
          this.flag = false
        }, 2000);
      },
      error: (err: Error) => {
        console.log(err.message);
      }
    }
    const observer = this.service.removeWeather(cityname, userName, country);

    observer.subscribe(obser);
  }

  goHome() {
    this.router.navigate([this.path]);
  }

}
