import {Injectable} from "@angular/core";
import {UserInStorage} from "./user-info.service";

export interface WeatherResponse{
  operationState:string;
  operationMessage:string;
  weather:WeatherInfo;
}

export interface WeatherInfo{
  city: CityInfo;
  data: Data;
}

export interface CityInfo{
  country:string;
  name:string;
}

export interface Data{
  dataMain:DataMain;
  date:Date;
  precipitation:Precipitation;
}

export interface DataMain{
  temp:string;
  pressure: string;
  humidity:string;
}

export interface Precipitation{
  description:string;
  main:string;
}


@Injectable()
export class WeatherInfoService{
  public currentWeatherKey: string = "currentWeather";
  public storage: Storage = sessionStorage;

  setWeather(weatherResponse:WeatherResponse){
    this.storage.setItem(this.currentWeatherKey, JSON.stringify(weatherResponse.weather));
  }

  getWeather():WeatherInfo{
    try {
      let userInfoString: string = this.storage.getItem(this.currentWeatherKey);
      if (userInfoString) {
        let weatherObj: WeatherInfo = JSON.parse(userInfoString);
        return weatherObj;
      } else {
        return null;
      }
    } catch (e) {
      return null;
    }
  }

  isWeatherLoaded():boolean{
    return !!this.storage.getItem(this.currentWeatherKey);
  }

  removeWeather(){
    this.storage.removeItem(this.currentWeatherKey);
  }
}
