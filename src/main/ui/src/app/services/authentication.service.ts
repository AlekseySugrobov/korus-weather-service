import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {environment} from "../../environments/environment";
import {User} from "../model/user.entity";
import {Router, Routes} from "@angular/router";

@Injectable()
export class AuthenticationService {
  constructor(private http: HttpClient, private route: Router) {
  }

  isLoggedIn = false;

  login(user: User) {
    return this.http.post<User>(environment.login, user)
    .subscribe((currentUser: User) => {
      if (!User.isNull(currentUser)) {
        this.isLoggedIn = true;
      } else {
        this.isLoggedIn = false;
      }
    });
  }
}
