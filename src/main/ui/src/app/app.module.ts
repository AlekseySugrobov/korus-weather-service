import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {AppRoutingModule} from "./app-routing.module";


import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
  MatButtonModule, MatCardModule, MatCheckboxModule, MatFormFieldModule, MatIconModule, MatInputModule,
  MatMenuModule, MatTableModule
} from "@angular/material";
import { SearchComponent } from './components/search/search.component';
import { MenuComponent } from './components/menu/menu.component';
import { LoginComponent } from './components/login/login.component';
import {LoginService} from "./services/api/login.service";
import {UserInfoService} from "./services/user-info.service";
import {ApiRequestService} from "./services/api/api-request.service";
import {AppConfig} from "./app-config";
import {HttpClientModule} from "@angular/common/http";
import {WeatherService} from "./services/api/weather.service";
import {WeatherInfoService} from "./services/weather-info.service";
import { WeatherInfoComponent } from './components/weather-info/weather-info.component';
import { HistoryComponent } from './components/history/history.component';
import {HistoryService} from "./services/api/history.service";


@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    MenuComponent,
    LoginComponent,
    WeatherInfoComponent,
    HistoryComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatMenuModule,
    MatIconModule,
    AppRoutingModule,
    HttpClientModule,
    MatCheckboxModule,
    MatCardModule,
    MatTableModule
  ],
  providers: [LoginService, UserInfoService, ApiRequestService, AppConfig, WeatherService, WeatherInfoService, HistoryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
