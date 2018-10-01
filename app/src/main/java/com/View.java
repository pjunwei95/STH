package com;

public class View {

    static void displayWelcomeModel() {
        System.out.println("What would you like to do?");
        System.out.println("    1. Create new model" + Message.DISPLAY_DEVELOPMENT);
        System.out.println("    2. Update existing model");
    }

    static void displayErrorInput() {
        System.out.println(Message.ERROR_INPUT);
        System.out.println(Message.INSTRUCTIONS);
    }

    static void displayWelcome() {
        System.out.println(Message.BORDER);
        System.out.println("Functions: ");
        System.out.println("    1. New/Update Skill");
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
