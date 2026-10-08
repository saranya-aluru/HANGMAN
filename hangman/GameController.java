import java.util.List;
import java.util.Scanner;

/**
 * Main application class.
 * CO5: Models real-world entities and relationships through effective OOP architecture.
 */
public class GameController {

    private final Scanner scanner = new Scanner(System.in);
    private final WordBank wordBank = new WordBank("words.txt");

    private enum Difficulty {
        EASY(9), // CO1: Data types and constants
        MEDIUM(7),
        HARD(5);

        private final int maxGuesses;
        Difficulty(int maxGuesses) { this.maxGuesses = maxGuesses; }
        public int getMaxGuesses() { return maxGuesses; }
    }

    public static void main(String[] args) {
        GameController controller = new GameController();
        controller.startGame();
    }

    public void startGame() {
        System.out.println("welcome to the escape room, to escape you have to play a game of hangman...");
System.out.println("all the best");

        // 1. Select Topic
        String topic = selectTopic();
        // 2. Select Difficulty
        Difficulty difficulty = selectDifficulty();
        
        // 3. Initialize Game
        String secretWord = wordBank.getRandomWord(topic);
        // CO5: Polymorphic object creation using the parent type and subclass implementation
        HangmanGame game = new ConsoleHangmanGame(secretWord, difficulty.getMaxGuesses()); 

        System.out.printf("\nGame Start! Topic: %s, Level: %s (Max Guesses: %d)\n", 
                          topic.toUpperCase(), difficulty, difficulty.getMaxGuesses());

        // 4. Main Game Loop (CO1: Iterative statements)
        while (!game.checkWinCondition() && !game.checkLoseCondition()) {
            game.displayGameStatus();
            char guess = getGuessInput();
            
            // CO1: Operator and logical construct
            if (!game.processGuess(guess)) { 
                System.out.printf("*** Oops! '%c' is NOT in the word. ***\n", guess);
            } else {
                System.out.printf("*** Good guess! '%c' is in the word. ***\n", guess);
            }
        }

        // 5. Game End
        game.displayGameStatus();
        if (game.checkWinCondition()) {
            System.out.println("\n🎉 CONGRATULATIONS! YOU WON,YOU ESCAPED THE ESCAPE ROOM:) 🎉");
        } else {
            System.out.println("\n💀 YOU LOST, YOU ARE STUCK WITH US FOREVER💀");
        }
        System.out.printf("The word was: **%s**\n", game.getSecretWord().toUpperCase());
        
        scanner.close();
        System.out.println("\nThanks for playing!");
    }

    // --- Helper Methods for Input ---

    private String selectTopic() {
        List<String> topics = wordBank.getTopics();
        while (true) {
            System.out.println("\nAvailable Topics:");
            // CO1: Iterative statements
            for (int i = 0; i < topics.size(); i++) {
                System.out.printf("  %d. %s\n", i + 1, topics.get(i).toUpperCase());
            }

            System.out.printf("Enter the number for your topic (1-%d): ", topics.size());
            try {
                // CO1: Reading input and conversion (type casting)
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= topics.size()) {
                    return topics.get(choice - 1);
                } else {
                    System.out.println("Invalid choice. Please enter a number from the list.");
                }
            } catch (NumberFormatException e) {
                // CO6: Exception handling
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private Difficulty selectDifficulty() {
        Difficulty[] levels = Difficulty.values();
        while (true) {
            System.out.println("\nAvailable Difficulties:");
            for (int i = 0; i < levels.length; i++) {
                System.out.printf("  %d. %s\n", i + 1, levels[i]);
            }

            System.out.printf("Enter the number for your difficulty (1-%d): ", levels.length);
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= levels.length) {
                    return levels[choice - 1];
                } else {
                    System.out.println("Invalid choice. Please enter a number from the list.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    private char getGuessInput() {
        while (true) {
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine().trim().toLowerCase();

            // CO1: Conditional and logical operators
            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                return input.charAt(0);
            } else {
                System.out.println("Please enter a single, valid English letter.");
            }
        }
    }
}