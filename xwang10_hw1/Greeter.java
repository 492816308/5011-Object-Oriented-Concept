package xwang10_hw1;
import java.util.Map;

public class Greeter {
    private Template template;

    // Constructor that initializes the template with the given string
    public Greeter(String s, ZippyQuote zippyQuote) {
        template = new Template(s, zippyQuote);
    }

    // Method to get the greeting by passing in a map of variables
    public String getGreeting(Map<String, String> vars) {
        return template.translate(vars);  // Call the translate method of Template to replace variables
    }

}
