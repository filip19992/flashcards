package com.flashcards.flashcards;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlashcardController {

    @GetMapping("/flashcard-random")
    public ResponseEntity<Flashcard> getRandomFlashcard() {
        return new ResponseEntity<>(new Flashcard("test question", "test answer"), HttpStatus.OK);
    }
}
