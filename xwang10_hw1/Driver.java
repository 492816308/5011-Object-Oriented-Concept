package xwang10_hw1;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        String file = "yow.lines";

        // Get user input for random or sequential mode
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 'r' for random mode or 's' for sequential mode: ");  // To check EC 1 problem
        String mode = scanner.nextLine().toLowerCase();

        boolean isRandom = mode.equals("r");

        // Initialize ZippyQuoteGenerator to load quotes from yow.lines file
        ZippyQuote zippyQuote = new ZippyQuote(file, isRandom);
        String msg = "Hello $name. I owe you $1.25. " +  // To check EC 2 problem.
                     "$newlineGood $daypart $name - that's a nice $color shirt. $newline Zippy Quote: $zippy";

        // Create a map for template variables and their values
        Map<String, String> templateVars = new HashMap<>();
//        templateVars.put("$name", "Xwang10");  // Comment out this line to check EC 3 problem.
//                                                     system username: Xiaoyuwang
        templateVars.put("$color", "yellow");
        templateVars.put("$newline", "\n");

        // Create a Greeter object, passing in the message template
        Greeter g = new Greeter(msg, zippyQuote, templateVars);

        // Get the final greeting by passing in the template variables map
        String greeting = g.getGreeting(templateVars);
        System.out.println(greeting);

        scanner.close();
    }
}

