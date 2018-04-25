import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {WeatherInfo, WeatherInfoService} from "../../services/weather-info.service";

@Component({
  selector: 'app-weather-info',
  templateUrl: './weather-info.component.html',
  styleUrls: ['./weather-info.component.css']
})
export class WeatherInfoComponent implements OnInit {

  constructor(private router: Router, private weatherInfoService: WeatherInfoService) { }

  ngOnInit() {
  }

  getWeatherInfo():WeatherInfo{
    return this.weatherInfoService.getWeather();
  }

  isWeatherLoaded(){
    return this.weatherInfoService.isWeatherLoaded();
  }

  backToSearch(){
    this.weatherInfoService.removeWeather();
    this.router.navigate(["/"]);
  }
}
