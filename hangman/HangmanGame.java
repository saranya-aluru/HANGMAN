import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * CO5: Design reusable classes employing inheritance, polymorphism, 
 * abstract classes, interfaces, and reflection (Used as an abstract class
 * for extensibility and Polymorphism example in GameController).
 */
public abstract class HangmanGame {

    protected final String secretWord;
    protected final int maxIncorrectGuesses;
    protected char[] guessedWord;
    protected Set<Character> guessedLetters;
    protected int incorrectGuesses;
    
    // Abstract method (CO5)
    public abstract void displayGameStatus();

    // Constructor (CO4)
    public HangmanGame(String word, int maxGuesses) {
        this.secretWord = word.toLowerCase();
        this.maxIncorrectGuesses = maxGuesses;
        this.guessedWord = new char[secretWord.length()];
        Arrays.fill(this.guessedWord, '_');
        this.guessedLetters = new HashSet<>();
        this.incorrectGuesses = 0;
    }

    // --- CO3: Advanced Logic (Recursion and Strings) ---
    
    /**
     * CO3: Uses recursion to check if the current guessed word matches the secret word.
     * This is an advanced problem-solving logic.
     */
    private boolean checkWinRecursive(int index) {
        // Base case: If we've checked the whole word and found no '_'
        if (index >= guessedWord.length) {
            return true;
        }
        // Recursive step: If the current character is '_', we haven't won
        if (guessedWord[index] == '_') {
            return false;
        }
        // Continue checking the next index
        return checkWinRecursive(index + 1);
    }
    
    public boolean checkWinCondition() {
        // Calls the recursive logic
        return checkWinRecursive(0);
    }

    public boolean checkLoseCondition() {
        // CO1: Conditional logic (if/else)
        return incorrectGuesses >= maxIncorrectGuesses;
    }
    
    // --- CO2: Data Structures and Algorithmic Approach (Array and Efficiency) ---
    
    /**
     * CO2: Applies logic-based solutions using one-dimensional array (guessedWord) 
     * to solve real-world problems.
     */
    public boolean processGuess(char guess) {
        guess = Character.toLowerCase(guess);
        
        // CO1: Conditional logic
        if (guessedLetters.contains(guess)) {
            System.out.println("You already guessed '" + guess + "'.");
            return false;
        }
        
        guessedLetters.add(guess);
        
        boolean correctGuess = false;
        // CO1: Iterative statements (for loop) for processing array
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == guess) {
                guessedWord[i] = guess;
                correctGuess = true;
            }
        }

        if (!correctGuess) {
            incorrectGuesses++;
            return false;
        }
        return true;
    }

    // --- Getters for display ---
    public String getWordDisplay() {
        // CO1: String manipulation and returning data
        return String.valueOf(guessedWord).replace("", " ").trim();
    }
    
    public String getGuessedLettersDisplay() {
        // CO1: Iterative statements and data presentation
        StringBuilder sb = new StringBuilder();
        for (char c : guessedLetters) {
            sb.append(c).append(", ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "None";
    }

    public String getSecretWord() { return secretWord; }
    public int getMaxIncorrectGuesses() { return maxIncorrectGuesses; }
    public int getIncorrectGuesses() { return incorrectGuesses; }
}

/**
 * CO4: Develop structured and modular programs by applying 
 * object-oriented programming principles such as encapsulation and abstraction.
 * This class inherits from HangmanGame to complete the implementation.
 */
class ConsoleHangmanGame extends HangmanGame {
    
    // Simple hangman ASCII art (indexed 0 to 6)
    private static final String[] HANGMAN_STAGES = {
        // 0 misses
        "   +---+\n   |   |\n       |\n       |\n       |\n      ===\n",
        // 1 miss
        "   +---+\n   |   |\n   O   |\n       |\n       |\n      ===\n",
        // 2 misses
        "   +---+\n   |   |\n   O   |\n   |   |\n       |\n      ===\n",
        // 3 misses
        "   +---+\n   |   |\n   O   |\n  /|   |\n       |\n      ===\n",
        // 4 misses
        "   +---+\n   |   |\n   O   |\n  /|\\  |\n       |\n      ===\n",
        // 5 misses
        "   +---+\n   |   |\n   O   |\n  /|\\  |\n  /    |\n      ===\n",
        // 6 misses
        "   +---+\n   |   |\n   O   |\n  /|\\  |\n  / \\  |\n      ===\n"
    };

    public ConsoleHangmanGame(String word, int maxGuesses) {
        super(word, maxGuesses);
    }

    // CO5: Implementation of the abstract method (Polymorphism)
    @Override
    public void displayGameStatus() {
        System.out.println("\n" + "=".repeat(40));
        
        // Use Math.min to safely index the hangman stages array (CO1)
        int index = Math.min(incorrectGuesses, HANGMAN_STAGES.length - 1);
        System.out.println(HANGMAN_STAGES[index]);
        
        System.out.println("Word:   " + getWordDisplay());
        System.out.println("Guesses Left: " + (maxIncorrectGuesses - incorrectGuesses));
        System.out.println("Guessed Letters: " + getGuessedLettersDisplay());
        System.out.println("=".repeat(40));
    }
}