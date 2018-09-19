package main;

import java.util.ArrayList;
import java.util.Scanner;

import static main.View.*;

public class Controller {
    Scanner sc;

    String processInput() {
        String command = sc.nextLine();
        while (!command.equals("1") && !command.equals("2")) {
            displayErrorInput();
            command = sc.nextLine();
        }
        return command;
    }

    void modelCommand() {
        System.out.println("What would you like to do?");
        System.out.println("    1. Create new model" + Message.DISPLAY_DEVELOPMENT);
        System.out.println("    2. Update existing model");
        String command = processInput();

        switch (command) {
            case "1": {
                displayUnderDevelopment();
                break;
            }
            case "2": {
                System.out.println("Which model would you like to update?");
                ArrayList<String> models = new ArrayList<>();
                System.out.printf("%d. %s\n", 1, CustomClassifier.category);
                String category = sc.nextLine();
                System.out.println("Category not available");
                displayUnderDevelopment();
                break;
            }
            default:
                break;
        }
    }


    public void controller() {
        displayWelcome();

        String command = processInput();

        switch (command) {
            case "1": {
                modelCommand();
                break;
            }
            case "2": {
                new CustomClassifier().run();
                break;
            }
            default:
                break;
        }
    }

    public Controller(Scanner sc) {
        this.sc = sc;
    }
}
