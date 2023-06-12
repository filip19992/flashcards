package com.flashcards.flashcards;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlashcardController {

    @GetMapping("/flashcard-random")
    public ResponseEntity<List<Flashcard>> getRandomFlashcard() {
        return new ResponseEntity<>(List.of(new Flashcard("house", "dom"),
                                            new Flashcard("dog", "pies"))
                , HttpStatus.OK);
    }
}
