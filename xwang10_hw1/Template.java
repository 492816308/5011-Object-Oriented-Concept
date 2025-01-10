package xwang10_hw1;

import java.util.Map;

public class Template {
    private String[] messages;
    private ZippyQuote zippyQuote;

    public Template(String s, ZippyQuote zippyQuote) {
        messages = s.split("\\s");
        this.zippyQuote = zippyQuote;
    }

    private boolean isVariable(String s) {
        return s.charAt(0) == '$';
    }

    public String translate(Map<String, String> vars) {
        StringBuilder sb = new StringBuilder();
        for (String msg : messages) {
            if (isVariable(msg)) {
                // Special handling for $newline (inserts a newline before the Zippy quote)
                if (msg.equals("$newline"))
                    sb.append("\n");
                // Special handling for $zippy (inserts a random Zippy quote)
                else if (msg.equals("$zippy"))
                    sb.append(zippyQuote.getRandomQuote());
                // Handle greeting according to current local time
                else if (msg.equals("$daypart"))
                    sb.append(new DayPart());
                // Handle other variables in the map
                else if (vars.containsKey(msg))
                    sb.append(vars.get(msg));
            }
            else
                sb.append(msg);
            sb.append(" ");
        }
        return sb.toString().trim();  // Remove any extra trailing spaces

        // version 2:
        /*
        String sa = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            if (isVariable(words[i]))
                sa[i] = vars.get(words[i]);
             else
                sa[i] = words[i];
        }
        return String.join(" ", sa);
         */
    }
}