package main;

import java.util.*;

//        Scoping of ict and skills from linkedin list of skills ad cope it out
//        Brain could be vendors, real world cannot have scope on some things
//        Mongodb query
//        Front-end framework for java->business users, CLI->developers
//        Free tier hosting: heroku, aws, RESTFUL for frontend
//        Containers that listen to https calls

public class SkillsTaxonomyHarmoniser {
    private Scanner sc = new Scanner(System.in);


    private void controller() {
        String function = sc.nextLine();
        while (!function.equals("1") && !function.equals("2")) {
            System.out.println(Message.INPUT_ERROR);
            function = sc.nextLine();
        }
        switch (function) {
            case "1": {
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
        System.out.println("Please select the options(no.) which you would like to use:");
        controller();
    }

    private void run() {
        System.out.println(Message.WELCOME_MESSAGE);
        try {
            while (true){
                view();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SkillsTaxonomyHarmoniser skillsTaxonomyHarmoniser = new SkillsTaxonomyHarmoniser();
        skillsTaxonomyHarmoniser.run();
    }
}
