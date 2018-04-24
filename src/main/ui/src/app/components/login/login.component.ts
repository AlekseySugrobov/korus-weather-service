import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {Credentials} from "../../model/credentials.entity";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials:Credentials = {username: '', password: ''};

  constructor(private auth: AuthenticationService, private router: Router) { }

  ngOnInit() {
  }

  login(){
    console.log(this.credentials);
    this.auth.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/');
    });
    return false;
  }

}
