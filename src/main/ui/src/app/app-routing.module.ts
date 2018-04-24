import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {SearchComponent} from "./components/search/search.component";
import {LoginComponent} from "./components/login/login.component";

const routes: Routes = [
  {path: "", component: SearchComponent},
  {path: "login", component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule{

}