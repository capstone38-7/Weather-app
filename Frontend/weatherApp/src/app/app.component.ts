import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from './service/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  cityControl:FormControl;
  countryControl :FormControl ; 
  search:FormGroup;

  
  path:string = `city-weather/${localStorage.getItem('currentCity')}/null`
  constructor(private fb:FormBuilder,private router:Router,private service: AuthenticationService){

  }


  ngOnInit(): void {
    this.cityControl = this.fb.control('',[Validators.required]);
    this.countryControl = this.fb.control('',[Validators.required]);
    this.search = this.fb.group({
      cityControl:this.cityControl,
      countryControl:this.countryControl
    });
  }

  loggedIn():boolean{
    return this.service.isUserLoggedIn();
  }



  searchValue(){
    const city = this.cityControl.value;
    const country = this.countryControl.value;
    const url = `/city-weather/${city}/${country}`; 
    this.router.navigate([url]);
    this.search.reset();
  }

  logout(){
    this.service.logout();
    this.router.navigate(['/login']);
    this.ngOnInit();
  }


}
