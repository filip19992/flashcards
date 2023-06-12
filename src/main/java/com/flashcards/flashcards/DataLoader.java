package com.flashcards.flashcards;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;

    private final FlashCardRepository flashCardRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, FlashCardRepository flashCardRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.flashCardRepository = flashCardRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(ApplicationArguments args) {
        var user = new User("test", passwordEncoder.encode("test"));
        userRepository.save(user);

        List<Flashcard> flashCards = Arrays.asList(
                new Flashcard("Question 1", "Answer 1"),
                new Flashcard("Question 2", "Answer 2"),
                new Flashcard("Question 3", "Answer 3"),
                new Flashcard("Question 4", "Answer 4"),
                new Flashcard("Question 5", "Answer 5")
        );

        flashCardRepository.saveAll(flashCards);
    }

    }




