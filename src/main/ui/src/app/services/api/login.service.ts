import {Injectable, Inject} from '@angular/core';
import {Router} from '@angular/router';

import {Observable, Subject, BehaviorSubject} from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {UserInfoService, LoginInfoInStorage} from '../user-info.service';
import {ApiRequestService} from './api-request.service';


export interface LoginRequestParam {
  username: string;
  password: string;
}

@Injectable()
export class LoginService {
  public landingPage = "/";

  constructor(private router: Router,
              private userInfoService: UserInfoService,
              private apiRequest: ApiRequestService) {
  }

  getToken(username: string, password: string): Observable<any> {
    let me = this;
    let bodyData: LoginRequestParam = {
      "username": username,
      "password": password
    }
    let loginDataSubject: BehaviorSubject<any> = new BehaviorSubject<any>([]);
    let loginInfoReturn: LoginInfoInStorage;

    this.apiRequest.post('session', bodyData)
      .subscribe(jsonResp => {
          console.log(jsonResp);
          if (jsonResp !== undefined && jsonResp !== null && jsonResp.operationState === "SUCCESS") {
            loginInfoReturn = {
              "success": true,
              "message": jsonResp.operationMessage,
              "landingPage": this.landingPage,
              "user": {
                "userId": jsonResp.item.userId,
                "email": jsonResp.item.emailAddress,
                "displayName": jsonResp.item.firstName + " " + jsonResp.item.lastName,
                "token": jsonResp.item.token,
                "role": jsonResp.item.role
              }
            };
            console.log(loginInfoReturn);
            this.userInfoService.storeUserInfo(JSON.stringify(loginInfoReturn.user));
          }
          else {
            loginInfoReturn = {
              "success": false,
              "message": jsonResp.msgDesc,
              "landingPage": "/login"
            };
          }
          loginDataSubject.next(loginInfoReturn);
        },
        err => {
          loginInfoReturn = {
            "success": false,
            "message": err.url + " >>> " + err.statusText + "[" + err.status + "]",
            "landingPage": "/login"
          };
        });

    return loginDataSubject;
  }

  logout(navigatetoLogout=true): void {
    this.userInfoService.removeUserInfo();
    if(navigatetoLogout){
      this.router.navigate(["/"]);
    }
  }
}
