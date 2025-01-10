package xwang10_hw1;

import java.util.Map;
import java.util.HashMap;

public class Driver {
    public static void main(String[] args) {

        String file = "yow.lines";
        // Initialize ZippyQuoteGenerator to load quotes from yow.lines file
        ZippyQuote zippyQuote = new ZippyQuote(file);
        String msg = "Good $daypart $name - that's a nice $color shirt. $newline Zippy Quote: $zippy";

        // Create a map for template variables and their values
        Map<String, String> templateVars = new HashMap<>();
        templateVars.put("$name", "Xwang10");
        templateVars.put("$color", "yellow");

        // Create a Greeter object, passing in the message template
        Greeter g = new Greeter(msg, zippyQuote);

        // Get the final greeting by passing in the template variables map
        String greeting = g.getGreeting(templateVars);
        System.out.println(greeting);
    }
}

