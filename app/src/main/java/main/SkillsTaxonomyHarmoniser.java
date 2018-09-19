package main;

import java.util.*;

//        Scoping of ict and skills from linkedin list of skills ad cope it out
//        Brain could be vendors, real world cannot have scope on some things
//        Mongodb query
//        Front-end framework for java->business users, CLI->developers
//        Free tier hosting: heroku, aws, RESTFUL for frontend
//        Containers that listen to https calls

// DONE read API key offline
// gradle integration with read API key DONE
// jackson dependencies DONE

public class SkillsTaxonomyHarmoniser {
    private Scanner sc = new Scanner(System.in);
    private Controller sthController;
    boolean isLoop = false;


    void misc() {

    }

    private void executeController(){
        sthController = new Controller(sc);
        sthController.controller();
        misc();
    }

    private void run() {
        System.out.println(Message.WELCOME_MESSAGE);
        try {
            while (true){
                executeController();
                Thread.sleep(1000);
                if (!isLoop)
                    break;
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
