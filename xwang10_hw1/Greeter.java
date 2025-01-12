package xwang10_hw1;
import java.util.Map;

public class Greeter {
    private Template template;

    // Constructor that initializes the template with the given string
    public Greeter(String s, ZippyQuote zippyQuote, Map<String, String> inputVars) {
        // Check if the name is in the inputVars, otherwise, get the system username
        String name = inputVars.getOrDefault("$name", getUserName());
        inputVars.put("$name", name);  // Ensure $name is put in the dictionary
                                        // (even if retrieved via the system username)
        template = new Template(s, zippyQuote);
    }

    // Method to get the system username, formatted properly
    public String getUserName() {
        String userName = System.getProperty("user.name", "Unnamed Person");
        if (userName.equals("Unnamed Person")) {
            return userName;
        } else {
            // Capitalize the first letter of the username
            return userName.substring(0, 1).toUpperCase() + userName.substring(1);
        }
    }

    // Method to get the greeting by passing in a map of variables
    public String getGreeting(Map<String, String> vars) {
        return template.translate(vars);  // Call the translate method of Template to replace variables
    }

}
