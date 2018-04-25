import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../../services/api/login.service";
import {UserInfoService} from "../../services/user-info.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials:any = {username: '', password: ''};
  errMsg:string = '';

  constructor(private router: Router, private loginService: LoginService, private userInfoService: UserInfoService) { }

  ngOnInit() {
    this.loginService.logout(false);
  }

  login(){
    this.loginService.getToken(this.credentials.username, this.credentials.password)
      .subscribe(resp => {
          if (resp.user === undefined || resp.user.token === undefined || resp.user.token === "INVALID" ){
            this.errMsg = 'Имя пользователя или пароль некорректны';
            return;
          }
          this.router.navigate([resp.landingPage]);
        },
        errResponse => {
          switch(errResponse.status){
            case 401:
              this.errMsg = 'Имя пользователя или пароль некорректны';
              break;
            case 404:
              this.errMsg = 'Не найдена служба авторизации';
            case 408:
              this.errMsg = 'Превышено время ожидания ответа';
            case 500:
              this.errMsg = 'Внутрення ошибка сервера';
            default:
              this.errMsg = 'Ошибка сервера';
          }
        }
      );
  }

  loggedIn():boolean{
    return this.userInfoService.isLoggedIn();
  }

}
