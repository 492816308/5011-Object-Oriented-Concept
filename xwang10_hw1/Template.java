package xwang10_hw1;

import java.util.Map;

public class Template {
    private String[] messages;
    private ZippyQuote zippyQuote;

    public Template(String s, ZippyQuote zippyQuote) {
        messages = s.split("\\s");
        this.zippyQuote = zippyQuote;
    }

//    // Method to check if a string  is a valid variable
//    private boolean isVariable(String s) {
//        return true;
//    }

    public String translate(Map<String, String> vars) {
        StringBuilder sb = new StringBuilder();
        for (String msg : messages) {
            // Special handling for $zippy (inserts a random or sequential Zippy quote)
            if (msg.equals("$zippy"))
                sb.append(zippyQuote.getNextQuote());
            // Handle greeting according to current local time
            else if (msg.equals("$daypart"))
                sb.append(new DayPart());

            // Check if msg starts with key in the map
            else {
                boolean match = false;
                // msg #1 : $newlineGood  -> \nGood
                // msg #2: $name. -> Xiaoyuwang.
                for(String key : vars.keySet()) {
                    if (msg.startsWith(key)) {
                        sb.append(vars.get(key)); // \n
                        sb.append(msg.substring(key.length()));
                        match = true;
                        break;
                    }
                }
                if(!match)  // msg: #3: $1.25 (no key found)
                    sb.append(msg);
            }
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