package xwang10_hw1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class ZippyQuote {
    private List<String> quotes;
    private Random random;
    private int currentIndex;
    private boolean isRandomMode;

    // Constructor to load Zippy quotes from the yow.lines file and set to mode (random or sequential)
    public ZippyQuote(String filename, boolean isRandomMode) {
        try {
            quotes = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.random = new Random();
        this.currentIndex = 0;
        this.isRandomMode = isRandomMode;
    }

    // Method to get a Zippy quote (random or sequential)
    public String getNextQuote() {
        if (quotes.isEmpty())
            return "No quotes available."; // Return a default message if no quotes are loaded
        if (isRandomMode)  // random mode
            return quotes.get(random.nextInt(quotes.size()));  // Select a random quote
        else {
            // sequential mode
            String quote = quotes.get(currentIndex);
            currentIndex = (currentIndex + 1) % quotes.size(); // loop back to the first quote
            return quote;
        }
    }
}
