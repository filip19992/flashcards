import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {catchError, Observable, tap, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    const url = 'http://localhost:8080/login';

    const body = { username, password };

    return this.http.post(url, body, { observe: 'response', withCredentials: true }).pipe(
      tap((response: any) => {
        console.log(response);

        const token = response.headers.get('Authorization');
        if (token) {
          localStorage.setItem('authToken', token);
        } else {
          console.error('Authorization token not found in response headers.');
        }
      }),
      catchError((error: any) => {
        console.error('Error occurred during login:', error);
        return throwError('Login failed.');
      })
    );
  }

}
