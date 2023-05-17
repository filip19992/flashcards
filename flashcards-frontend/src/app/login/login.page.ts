import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: 'login.page.html',
  styleUrls: ['login.page.scss'],
})
export class LoginPage {
  username!: string;
  password!: string;

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    // Call the authentication service's login method
    this.authService.login(this.username, this.password).subscribe(
      () => {
        // Redirect to the home page or any other protected page
        this.router.navigate(['/home']);
      },
      (error) => {
        // Handle login error, display appropriate message to the user
        console.error('Login failed:', error);
      }
    );
  }
}
