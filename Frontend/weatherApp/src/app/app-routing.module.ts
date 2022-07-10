import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { CityWeatherComponent } from './citydetails/citydetails.component';
import { LoginGuard } from './guards/login-guard.guard';
import { ListCitiesWeatherComponent } from './list-cities-weather/list-cities-weather.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
  {
    path:"",redirectTo:'login',pathMatch:'full'
  },
  {
    path:"city-weather/:city/:country",component:CityWeatherComponent ,canActivate:[LoginGuard]
  },
  {
    path:'listCities',component:ListCitiesWeatherComponent,canActivate:[LoginGuard]
  },
  { 
    path:"login",component:LoginComponent
  },
  {
    path:"register",component:SignupComponent
  },
  {
    path:"**",component:LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
