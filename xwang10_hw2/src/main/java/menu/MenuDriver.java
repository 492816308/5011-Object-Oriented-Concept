package menu;

/**
 * Reference: <a href="https://www.java-forums.org/new-java/26584-menu-driven-console-help-please.html">A Simple Text-Based Menu System</a>
 * @author JosAH
 */
public class MenuDriver {
    
    private static MenuItem item1 = new MenuItem("Add new user", new Runnable() {
        public void run() {
            System.out.println("running Add new user");
        }
    });
     
    private static MenuItem item2 = new MenuItem("User adds site", new Runnable() {
        public void run() {
            System.out.println("running User adds site");
        }
    });
 
    private static MenuItem item3 = new MenuItem("User requests new password for site", new Runnable() {
        public void run() {
            System.out.println("running User requests new password for site");
        }
    });
 
    private static Menu nestedMenu = new Menu("nested menu", true, false, item2, item3);
    private static Menu topMenu = new Menu("top menu", false, true, item1, nestedMenu);
 
    public static void main(String[] args) {

        topMenu.run();
    }
}
