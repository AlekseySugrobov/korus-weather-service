import { Component, OnInit } from '@angular/core';
import {UserInfoService} from "../../services/user-info.service";
import {MatCheckboxChange} from "@angular/material";
import {WeatherService} from "../../services/api/weather.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  city:string = "";
  byCoords:boolean = false;
  longitude:string = "";
  lattitude:string = "";
  errMsg:string="";

  constructor(private userInfoService: UserInfoService, private weatherSerice: WeatherService, private router:Router) { }

  ngOnInit() {
  }

  getName(){
    if(this.userInfoService.isLoggedIn()){
      return this.userInfoService.getUserInfo().displayName;
    } else {
      return "Гость";
    }
  }

  changeSearchParam(event:MatCheckboxChange){
    this.byCoords = event.checked;
  }

  searchWeather(){
    if(this.byCoords === false){
      this.weatherSerice.getWeatherByCity(this.city).subscribe(resp => {
        this.analyzeResp(resp);
      });
    } else {
      this.weatherSerice.getWeatherByCoordinates(this.longitude, this.lattitude).subscribe(resp => {
        this.analyzeResp(resp);
      });
    }
  }

  private analyzeResp(resp){
    if(resp && resp.operationState === "SUCCESS"){
      this.router.navigate(["weatherInfo"]);
    } else {
      this.errMsg = "Невозможно загрузить погоду по указанной информации";
    }
  }
}
