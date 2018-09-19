package main;

public class View {

    static void displayErrorInput() {
        System.out.println(Message.ERROR_INPUT);
        System.out.println(Message.INSTRUCTIONS);
    }

    static void displayWelcome() {
        System.out.println(Message.BORDER);
        System.out.println("Functions: ");
        System.out.println("    1. New/Update Model");
        System.out.println("    2. Harmonise the skills");
        System.out.println(Message.INSTRUCTIONS);
    }

    static void displayUnderDevelopment() {
        System.out.println(Message.DISPLAY_DEVELOPMENT.trim());
        System.out.println("Exiting...");
        System.out.println(Message.BORDER);
        System.out.println("Going back to main menu");
    }

}
