import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    const url = 'http://localhost:8080/login'; // Replace with your Spring Security login endpoint

    // Create a request body with the username and password
    const body = { username, password };

    // Send a POST request to the login endpoint
    return this.http.post(url, body, { withCredentials: true });
  }

}

