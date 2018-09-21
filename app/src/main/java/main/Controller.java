package main;

import java.util.ArrayList;
import java.util.Scanner;

import static main.View.*;

public class Controller {
    Scanner sc;
    CustomClassifier customClassifier;

    String processInput() {
        String command = sc.nextLine();
        while (!command.equals("1") && !command.equals("2")) {
            displayErrorInput();
            command = sc.nextLine();
        }
        return command;
    }

    void modelCommand() {
        displayWelcomeModel();

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


    public void welcomeCommand() {
        displayWelcome();

//        String command = processInput();
        String command = sc.nextLine();


        while (true) {
            switch (command) {
                case "1": {
                    modelCommand();
                    break;
                }
                case "2": {
                    customClassifier = new CustomClassifier();
                    customClassifier.run();
                    break;
                }
                default: {
                    displayErrorInput();
                    command = sc.nextLine();
//                    break;
                    break;
                }
            }
        }
    }

    public Controller(Scanner sc) {
        this.sc = sc;
    }
}
