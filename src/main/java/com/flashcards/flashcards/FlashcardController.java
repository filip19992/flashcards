package com.flashcards.flashcards;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlashcardController {
    private final FlashCardRepository flashCardRepository;

    public FlashcardController(FlashCardRepository flashCardRepository) {
        this.flashCardRepository = flashCardRepository;
    }

    @GetMapping("/flashcard-random")
    public ResponseEntity<List<Flashcard>> getRandomFlashcard() {
        List<Flashcard> flashCards = flashCardRepository.findAll();
        return new ResponseEntity<>(flashCards, HttpStatus.OK);
    }
}
