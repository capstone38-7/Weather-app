import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListCitiesWeatherComponent } from './list-cities-weather/list-cities-weather.component';
import { CityWeatherComponent } from './citydetails/citydetails.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ShowWeekDayPipe } from './pipe/show-week-day.pipe';
import { LoginComponent } from './login/login.component';
import { AuthenticationInterceptor } from './request.interceptor';
import { SignupComponent } from './signup/signup.component';

@NgModule({
  declarations: [
    AppComponent,
    ListCitiesWeatherComponent,
    CityWeatherComponent,
    ShowWeekDayPipe,
    LoginComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [CityWeatherComponent,
    {
      provide:HTTP_INTERCEPTORS,
      useClass:AuthenticationInterceptor,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
