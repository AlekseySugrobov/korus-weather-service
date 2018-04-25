import {Injectable} from "@angular/core";

@Injectable()
export class AppConfig{
  public version:string = "1.0.0";
  public locale:string  = "ru-RU";
  public dateFormat     = { year:'numeric', month: 'short', day: 'numeric'};
  public baseApiPath:string = "http://localhost:8080/";

  constructor(){
    if (this.locale===undefined){
      this.locale = navigator.language;
    }
  }
}
