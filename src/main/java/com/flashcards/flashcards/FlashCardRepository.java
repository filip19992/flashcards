package com.flashcards.flashcards;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashCardRepository extends JpaRepository<Flashcard,Long> {

    List<Flashcard> findAll() ;


}
