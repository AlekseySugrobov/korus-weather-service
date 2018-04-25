import { Component, OnInit } from '@angular/core';
import {HistoryRecord, HistoryService} from "../../services/api/history.service";
import {MatTableDataSource} from "@angular/material";
import {UserInfoService} from "../../services/user-info.service";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  history:HistoryRecord[];
  constructor(private historyService: HistoryService, private userInfoService: UserInfoService) { }

  ngOnInit() {
    this.getHistory()
  }

  isAdmin(){
    return this.userInfoService.getUserRole() === "ADMIN";
  }


  private getHistory(){
    this.historyService.getHistory().subscribe(resp => {
      this.history = resp.history;
      console.log(resp.history);
      console.log(this.history);
    });
  }

}
