package com.main;

import com.controller.Controller;
import com.view.Message;

public class SkillsTaxonomyHarmoniser {
    // rules
    static boolean isLoop = true;
    public static boolean isDebug = false;
    public static boolean isApiKeyEnabled = true;

    private Controller sthController;

    private void executeController(){
        sthController = new Controller();
        sthController.welcomeCommand();
    }

    private void run() {
        System.out.println(Message.FANCY_NAME);
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
