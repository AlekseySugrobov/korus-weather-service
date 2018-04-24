import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable()
export class AuthenticationService{
  constructor(private http: HttpClient){}

  authenticate(credentials, callback){
    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});
    this.http.get('user', {headers: headers}).subscribe( response => {
      if(response['name']){
        console.log('authenticated');
      } else {
        console.log('not authenticated');
      }
      return callback && callback();
    });
  }
}
