import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

interface Flashcard {
  question: string;
  answer: string;
}

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {
  flashcardData!: Flashcard;
  showAnswer: boolean = false;
  authToken: string | null = null; // Set the authentication token value

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getFlashcardData();
  }

  getFlashcardData() {
    const url = 'http://localhost:8080/flashcard-random';

    // Retrieve the token from local storage or any other storage mechanism
    // For example:
    this.authToken = localStorage.getItem('authToken');
    // Assign the retrieved token directly to the class-level property

    if (this.authToken) {
      // If the retrieved token exists, proceed with the request
      const headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.authToken);

      this.http.get<Flashcard>(url, { headers }).subscribe(
        (data) => {
          this.flashcardData = data;
        },
        (error) => {
          console.log(error);
        }
      );
    } else {
      // If the authToken is not available, handle the scenario appropriately
      console.log('Token not found');
      return;
    }
  }

}
