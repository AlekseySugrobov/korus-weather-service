import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {SearchComponent} from "./components/search/search.component";
import {LoginComponent} from "./components/login/login.component";
import {WeatherInfoComponent} from "./components/weather-info/weather-info.component";
import {HistoryComponent} from "./components/history/history.component";

const routes: Routes = [
  {path: "", component: SearchComponent},
  {path: "login", component: LoginComponent},
  {path: "weatherInfo", component: WeatherInfoComponent},
  {path: "history", component: HistoryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule{

}
