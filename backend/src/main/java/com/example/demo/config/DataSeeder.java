package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Flashcard;
import com.example.demo.entity.StudyDeck;
import com.example.demo.entity.SystemUser;
import com.example.demo.repository.FlashcardRepository;
import com.example.demo.repository.StudyDeckRepository;
import com.example.demo.repository.SystemUserRepository;

@Component
public class DataSeeder implements CommandLineRunner{
    @Autowired
    private SystemUserRepository systemUserRepository;
    @Autowired
    private StudyDeckRepository studyDeckRepository;
    @Autowired
    private FlashcardRepository flashcardRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    @Override
    public void run(String... args) {
        SystemUser linguist = seedUser("linguist1", "password123", SystemUser.Role.LINGUIST);
        seedUser("learner1", "password123", SystemUser.Role.LEARNER);
        seedUser("admin1", "password123", SystemUser.Role.ADMIN);
 
        seedDeckWithCards(linguist);
    }
 
    private SystemUser seedUser(String username, String rawPassword, SystemUser.Role role) {
        return systemUserRepository.findByUsername(username)
                .orElseGet(() -> {
                    SystemUser user = new SystemUser(username, passwordEncoder.encode(rawPassword), role);
                    return systemUserRepository.save(user);
                });
    }
 
    private void seedDeckWithCards(SystemUser owner) {
        if (!studyDeckRepository.findByTitleContainingIgnoreCase("Basic Greetings").isEmpty()) {
            return;
        }
 
        StudyDeck deck = new StudyDeck(
                "Basic Greetings",
                "Common greetings in Spanish for beginners",
                50,
                owner.getUsername(),
                owner.getId()
        );
        StudyDeck savedDeck = studyDeckRepository.save(deck);
 
        flashcardRepository.save(new Flashcard("Hello", "Hola", 1, savedDeck));
        flashcardRepository.save(new Flashcard("Good morning", "Buenos dias", 2, savedDeck));
        flashcardRepository.save(new Flashcard("Thank you", "Gracias", 3, savedDeck));
    }
}
