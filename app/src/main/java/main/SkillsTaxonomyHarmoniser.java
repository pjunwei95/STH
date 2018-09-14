package main;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

//        Scoping of ict and skills from linkedin list of skills ad cope it out
//        Brain could be vendors, real world cannot have scope on some things
//        Mongodb query
//        Front-end framework for java->business users, CLI->developers
//        Free tier hosting: heroku, aws, RESTFUL for frontend
//        Containers that listen to https calls

//TODO read API key offline
// gradle integration with read API key DONE
// jackson dependencies DONE

public class SkillsTaxonomyHarmoniser {
    public Scanner sc = new Scanner(System.in);

    //Helpers

    void displayUnderDevelopment() {
        System.out.println(Message.DISPLAY_DEVELOPMENT.trim());
        System.out.println("Exiting...");
        System.out.println(Message.BORDER);
        System.out.println("Going back to main menu");
    }

    String processInput() {
        String command = sc.nextLine();
        while (!command.equals("1") && !command.equals("2")) {
            System.out.println(Message.ERROR_INPUT);
            System.out.println(Message.INSTRUCTIONS);
            command = sc.nextLine();
        }
        return command;
    }

    void misc() {
        Path apiFilePath = Paths.get("api_key.xml");
        File apiFile = new File(apiFilePath.toUri());

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

    private void controller() {
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


    /*
    *   This method provides the view of the CLI interface
     */
    private void view(){
        System.out.println(Message.BORDER);
        System.out.println("Functions: ");
        System.out.println("    1. New/Update Model");
        System.out.println("    2. Harmonise the skills");
        System.out.println(Message.INSTRUCTIONS);
        controller();
        misc();
    }

    private void run() {
        System.out.println(Message.WELCOME_MESSAGE);
        try {
            while (true){
                view();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SkillsTaxonomyHarmoniser skillsTaxonomyHarmoniser = new SkillsTaxonomyHarmoniser();
        skillsTaxonomyHarmoniser.run();
    }
}
