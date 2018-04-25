import { Component, OnInit } from '@angular/core';
import {UserInfoService} from "../../services/user-info.service";
import {LoginService} from "../../services/api/login.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(private userInfoService: UserInfoService, private loginService: LoginService) { }

  ngOnInit() {
  }

  isLogged():boolean{
    return this.userInfoService.isLoggedIn();
  }

  getFullName():string{
    return this.userInfoService.getUserInfo().displayName;
  }

  logout(){
    this.loginService.logout();
  }
}
