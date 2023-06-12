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
  flashcardData: Flashcard[] = [];
  selectedFlashcard: Flashcard | null = null;
  showAnswer: boolean = false;
  authToken: string | null = null;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getFlashcardData();
  }

  getFlashcardData() {
    const url = 'http://localhost:8080/flashcard-random';

    this.authToken = localStorage.getItem('authToken');

    if (this.authToken) {
      const headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.authToken);

      this.http.get<Flashcard[]>(url, { headers }).subscribe(
        (data) => {
          this.flashcardData = data;
        },
        (error) => {
          console.log(error);
        }
      );
    } else {
      console.log('Token not found');
      return;
    }
  }
  onFlashcardClick(flashcard: Flashcard) {
    this.selectedFlashcard = flashcard;
    this.showAnswer = true;
  }
}
