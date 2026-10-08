import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * CO6: Implement robust, scalable, and generic Java applications integrating 
 * exception handling, file I/O, generics, and collections framework.
 */
public class WordBank {
    // Map to store words: Topic (String) -> List of Words (List<String>)
    private final Map<String, List<String>> topicWords = new HashMap<>();
    private final Random random = new Random();

    // Constructor handles file reading and exception handling (CO6)
    public WordBank(String filename) {
        System.out.println("Loading word bank from " + filename + "...");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String currentTopic = null;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("#")) {
                    // This line is a topic header
                    currentTopic = line.substring(1).toLowerCase();
                } else if (currentTopic != null) {
                    // This line contains words for the current topic
                    String[] words = line.toLowerCase().split(",");
                    // Use Arrays.asList to convert array to List for the Collection Framework (CO6)
                    topicWords.put(currentTopic, Arrays.asList(words));
                    currentTopic = null; // Reset for next topic block
                }
            }
            if (topicWords.isEmpty()) {
                throw new IOException("Word bank loaded but is empty. Check file format.");
            }
            System.out.println("Word bank loaded successfully. Topics: " + topicWords.keySet());
        } catch (IOException e) {
            // Robust exception handling for I/O errors (CO6)
            System.err.println("FATAL ERROR: Could not load word bank.");
            System.err.println("Ensure 'words.txt' exists in the current directory and is correctly formatted.");
            e.printStackTrace();
            // Exit the application gracefully if data loading fails
            System.exit(1); 
        }
    }

    public List<String> getTopics() {
        // Returns a list of available topics (CO6 - Collections)
        return new java.util.ArrayList<>(topicWords.keySet());
    }

    public String getRandomWord(String topic) {
        List<String> words = topicWords.get(topic.toLowerCase());
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("Topic not found or is empty: " + topic);
        }
        // Returns a random word from the list
        return words.get(random.nextInt(words.size()));
    }
}