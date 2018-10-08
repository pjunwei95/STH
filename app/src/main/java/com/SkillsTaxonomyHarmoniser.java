package com;

import java.util.*;

public class SkillsTaxonomyHarmoniser {
    // rules
    static boolean isLoop = false;
    static boolean isApiKeyEnabled = true;

    //Initialised at start
    private Scanner sc = new Scanner(System.in);
    private Controller sthController;


    private void executeController(){
        sthController = new Controller(sc);
        sthController.welcomeCommand();
    }

    private void run() {
        System.out.println(Message.WELCOME_MESSAGE);
        try {
            while (true){
//                model.run();
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
