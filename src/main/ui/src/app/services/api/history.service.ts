import {ApiRequestService} from "./api-request.service";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Injectable} from "@angular/core";

export class HistoryRecord{
  userAction:string;
  description:string;
  actionDate:Date;
  user:any;
}

@Injectable()
export class HistoryService{

  constructor(private apiRequest: ApiRequestService){}

  getHistory(): Observable<any>{
    let historySubject: BehaviorSubject<any> = new BehaviorSubject<any>([]);
    this.apiRequest.get("/api/getHistory").subscribe(jsonResp => {
      historySubject.next(jsonResp);
    })
    return historySubject;
  }
}
