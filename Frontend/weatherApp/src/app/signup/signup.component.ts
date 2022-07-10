import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  nameCtrl: FormControl;
  passwordCtrl: FormControl;
  currentCityCtrl: FormControl;
  registerGroup: FormGroup;

  constructor(private builder: FormBuilder,private route:Router,private authService:AuthenticationService) {
    this.nameCtrl = builder.control('',[Validators.required,Validators.minLength(2)]);
    this.passwordCtrl = builder.control('',[Validators.required,Validators.minLength(8),Validators.maxLength(12)]);
    this.currentCityCtrl = builder.control('',[Validators.required,Validators.minLength(2)]);
    this.registerGroup = builder.group({
      name: this.nameCtrl,
      password: this.passwordCtrl,
      currentCity: this.currentCityCtrl
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const name = this.nameCtrl.value;
    const password = this.passwordCtrl.value;
    localStorage.setItem('currentCity',this.currentCityCtrl.value);

    this.authService.register(name,password).subscribe({
      next:(data:any)=>{
        this.route.navigate(['/login']);
      }
    });
  }

}
