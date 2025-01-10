package xwang10_hw1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class ZippyQuote {
    private List<String> quotes;
    private Random random;

    // Constructor to load Zippy quotes from the yow.lines file
    public ZippyQuote(String filename) {
        try {
            quotes = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        random = new Random();
    }

    // Method to get a random Zippy quote
    public String getRandomQuote() {
        if (quotes.isEmpty())
            return "No quotes available."; // Return a default message if no quotes are loaded
        return quotes.get(random.nextInt(quotes.size()));  // Select a random quote
    }
}
