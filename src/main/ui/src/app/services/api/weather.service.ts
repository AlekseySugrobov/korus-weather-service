import {Injectable} from "@angular/core";
import {WeatherInfoService, WeatherResponse} from "../weather-info.service";
import {ApiRequestService} from "./api-request.service";
import {HttpParams} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs/Rx";

export interface CoordinatesRequestParam{
  longitude: string;
  lattitude: string;
}

@Injectable()
export class WeatherService{
  constructor(
    private weatherInfoService: WeatherInfoService,
    private apiRequest: ApiRequestService
  ){}

  getWeatherByCity(city:string) : Observable<any>{
    let params = new HttpParams()
      .set('city', city);
    let weatherSubject: BehaviorSubject<any> = new BehaviorSubject<any>([]);
    this.apiRequest.get('api/weatherByCity', params)
      .subscribe((jsonResp:WeatherResponse) => {
        this.weatherInfoService.setWeather(jsonResp);
        weatherSubject.next(jsonResp);
      });
    return weatherSubject;
  }

  getWeatherByCoordinates(longitude: string, lattitude: string) : Observable<any>{
    let bodyData: CoordinatesRequestParam = {
      "longitude": longitude,
      "lattitude": lattitude
    };
    let weatherSubject: BehaviorSubject<any> = new BehaviorSubject<any>([]);
    this.apiRequest.post('api/weatherByCoordinates', bodyData)
      .subscribe((jsonResp:WeatherResponse) =>  {
        this.weatherInfoService.setWeather(jsonResp);
        weatherSubject.next(jsonResp);
      });
    return weatherSubject;
  }
}
