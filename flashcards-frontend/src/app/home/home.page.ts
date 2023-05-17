import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getFlashcardData();
  }

  getFlashcardData() {
    const url = 'http://localhost:8080/flashcard-random';
    this.http.get<Flashcard>(url).subscribe((data) => {
      this.flashcardData = data;
    });
  }
}
