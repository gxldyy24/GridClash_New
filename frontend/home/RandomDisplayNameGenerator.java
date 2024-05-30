package edu.skidmore.cs326.game.sudoku.frontend.home;

import java.util.Random;

import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.insert.UserValidation;

/**
 * A class that generates random user display names upon request.
 */
public class RandomDisplayNameGenerator {
	
	/**
	 * Constructor for this class.
	 */
	private RandomDisplayNameGenerator() {
	}

    /**
     * Verb Array.
     */
    private static final String[] VERBS =
    	{"Flying", "Jumping", "Dancing", "Singing", "Laughing", "Running"};
    
    /**
     * Colors Array.
     */
    private static final String[] COLORS =
    	{"Red", "Blue", "Green", "Yellow", "Orange", "Purple"};
    
    /**
     * Emotions Array.
     */
    private static final String[] EMOTIONS = 
    	{"Happy", "Sad", "Angry", "Excited", "Calm", "Surprised"};
    
    /**
     * Nouns Array.
     */
    private static final String[] NOUNS =
    	{"Cow", "Cat", "Dog", "Bird", "Fish", "Tiger"};

    /**
     * Generate a random number between 1 and 99.
     * 
     * @return random
     */
    private static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(99) + 1;
    }

    /**
     * Generate a random display name.
     * 
     * @return username or new random name
     * @throws PersistenceException 
     */
    public static String generateRandomDisplayName() 
                throws PersistenceException {
        Random random = new Random();
      
        // Select random verb, color, emotion, and noun from arrays
        String verb = VERBS[random.nextInt(VERBS.length)];
        String color = COLORS[random.nextInt(COLORS.length)];
        String emotion = EMOTIONS[random.nextInt(EMOTIONS.length)];
        String noun = NOUNS[random.nextInt(NOUNS.length)];

        // Generate random number
        int number = generateRandomNumber();
        
        // Combine elements to form display name
        String username = verb + color + emotion + noun + number;
        if (UserValidation.checkUsername(username)) {
            return username;
        }
        return generateRandomDisplayName();
    }
}

