import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { baseServerUrl } from '../model/fakedata';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(private client: HttpClient) { }

  isUserLoggedIn(): boolean {
    const token = this.getToken();
    return token != undefined && token != null;
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
  }


  getUsername(): string {
    const username = localStorage.getItem("username");
    return username;
  }

  getToken(): string {
    const token = localStorage.getItem('token');
    return token;
  }


  login(username: string, password: string): Observable<string> {
    const url = baseServerUrl + "/login";
    const requestData = { username, password };

    const observable: Observable<string> = this.client.post(url, requestData, { responseType: "text" });
    return observable;
  }

  register(username: string, password: string): Observable<string> {
    const url = baseServerUrl + "/register";
    const requestData = { username, password };

    const observable: Observable<string> = this.client.post(url, requestData, { responseType: "text" });
    return observable;
  }


  saveToken(username: string, token: string) {
    localStorage.setItem('username', username);
    localStorage.setItem('token', token);
  }

}
