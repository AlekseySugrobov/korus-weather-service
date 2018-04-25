import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {AppRoutingModule} from "./app-routing.module";


import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
  MatButtonModule, MatFormFieldModule, MatIconModule, MatInputModule,
  MatMenuModule
} from "@angular/material";
import { SearchComponent } from './components/search/search.component';
import { MenuComponent } from './components/menu/menu.component';
import { LoginComponent } from './components/login/login.component';
import {LoginService} from "./services/api/login.service";
import {UserInfoService} from "./services/user-info.service";
import {ApiRequestService} from "./services/api/api-request.service";
import {AppConfig} from "./app-config";
import {HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    MenuComponent,
    LoginComponent
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
    HttpClientModule
  ],
  providers: [LoginService, UserInfoService, ApiRequestService, AppConfig],
  bootstrap: [AppComponent]
})
export class AppModule { }
