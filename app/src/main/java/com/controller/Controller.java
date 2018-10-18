package com.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.CustomClassifier;
import com.DecisionTree;
import com.JsonResponseHandler;
import com.model.ConfidenceResponse;
import com.model.Skill;
import com.model.SkillCategory;
import com.model.Taxonomy;

import static com.DecisionTree.CONFIDENCE_LOWER_LIMIT;
import static com.DecisionTree.CONFIDENCE_UPPER_LIMIT;
import static com.view.View.*;
import okhttp3.Response;

public class Controller {
    private Scanner sc;
    private Database database;


    void updateSkills() {
        ArrayList<Skill> listOfSkills = database.fetchAllSkills();

        System.out.println("Here are the list of existing [IT] skills:");
        for (int i=0;i<listOfSkills.size();i++) {
            //1. IT:TSC->Application Development
            //1. Application Development (TSC)
            System.out.printf(" %d. %s (%s)\n",
                    i+1, listOfSkills.get(i).getName(),
                    listOfSkills.get(i).getBucketName().toUpperCase());
        }

        System.out.println("Which skill do you want to update?");

        int skillNum = Integer.parseInt(sc.nextLine());
        if (skillNum>listOfSkills.size() || skillNum<=0) {
            System.out.println("Please choose the skill index correctly!");
            skillNum = Integer.parseInt(sc.nextLine());
        }
        Skill skill = listOfSkills.get(skillNum-1);
//        System.out.println("You are updating the skill: ");
//        System.out.printf("[Infocomm Technology]->[%s]->[%s]\n",
//                skill.getBucketName().toUpperCase(), skill.getName());
        System.out.println("What do you want to do?");
        System.out.printf(" 1. Add keyword to [%s]\n", skill.getName());
        System.out.printf(" 2. Remove keyword from [%s]\n", skill.getName());
        String command = sc.nextLine();
        switch (command) {
            case "1":{
                System.out.println("What keyword do you want to add?");
                String keyword = sc.nextLine();
                database.addKeywordToSkill(keyword, skill.getName());
                break;
            }
            case "2": {
                System.out.println("What keyword do you want to remove?");
                String keyword = sc.nextLine();
                database.removeKeywordFromSkill(keyword, skill.getName());
                break;
            }
            default:
                break;
        }
    }

    void addSkillInBuckets() {
        displayChooseBuckets();
        String command = sc.nextLine();
        switch (command) {
            case "1": {
                System.out.println("You are accessing the TSC");
                database.addSkill(sc, "tsc");
                break;
            }
            case "2": {
                System.out.println("You are accessing the GSC");
                database.addSkill(sc, "gsc");
                break;
            }
            case "3": {
                System.out.println("You are accessing Noise");
                System.out.println("What keywords do you want to add?");
                String keyword = sc.nextLine();
                database.addKeywordToNoise(keyword);
                break;
            }
            default: {
                break;
            }
        }
    }

    void similarPromptCommand(Taxonomy[] taxonomyArray, String keyword){
        Taxonomy firstTaxonomy = taxonomyArray[0];
        Taxonomy secondTaxonomy = taxonomyArray[1];
        Taxonomy thirdTaxonomy = taxonomyArray[2];
        displaySimilarPrompt(taxonomyArray, keyword);
        String command = sc.nextLine();
        switch (command) {
            case "1":{
                database.addKeywordToSkill(keyword, firstTaxonomy.getTag());
                break;
            }
            case "2":{
                database.addKeywordToSkill(keyword, secondTaxonomy.getTag());
                break;
            }
            case "3":{
                database.addKeywordToSkill(keyword, thirdTaxonomy.getTag());
                break;
            }
            case "4":{
                database.addKeywordToNoise(keyword);
                break;
            }
            case "5":{
                break;
            }
            default:
                break;
        }

    }

    void decisionTreeCommand(ConfidenceResponse confidenceResponse, String keyword){
        DecisionTree decisionTree = new DecisionTree();
        Taxonomy[] taxonomyArray = decisionTree.execute(confidenceResponse);
        Taxonomy firstTaxonomy = taxonomyArray[0];
        Taxonomy secondTaxonomy = taxonomyArray[1];
        Taxonomy thirdTaxonomy = taxonomyArray[2];

        try {
            if (decisionTree.isSimilar(firstTaxonomy.getConfidence_score().doubleValue(),
                    secondTaxonomy.getConfidence_score().doubleValue(),
                    thirdTaxonomy.getConfidence_score().doubleValue()))
                similarPromptCommand(taxonomyArray, keyword);
            else if (firstTaxonomy.getConfidence_score().doubleValue() > CONFIDENCE_UPPER_LIMIT) {
                database.addKeywordToSkill(keyword, firstTaxonomy.getTag());
                Thread.sleep(1000);
            }
            else if (firstTaxonomy.getConfidence_score().doubleValue() < CONFIDENCE_LOWER_LIMIT) {
                database.addKeywordToNoise(keyword);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    boolean isKeywordInSkill(String keyword, ArrayList<Skill> listOfSkillsWithKeyword) {
        if (listOfSkillsWithKeyword.size() > 0) {
            System.out.printf("The skill [%s] already exists in the following skills:\n", keyword);
            int i=1;
            for (Skill skill : listOfSkillsWithKeyword) {
                System.out.println(i + ". " + skill.getName());
                i++;
            }
            System.out.println("Do you want to proceed to add?");
            System.out.println("    1. Yes");
            System.out.println("    2. Cancel");
            String command = sc.nextLine();
            switch (command) {
                case "1":
                    return true;
                case "2":
                    return false;
                default:
                    break;
            }
        }
        return true;
    }

    void sthCommand() {
        System.out.println("What keyword do you want to add to the skill framework?");
        String keyword = sc.nextLine();
        ArrayList<Skill> listOfSkillsWithKeyword = database.fetchAllSkillsWithKeyword(keyword);

        boolean isCustomClassifier = isKeywordInSkill(keyword, listOfSkillsWithKeyword);

        if(isCustomClassifier)
            customClassifierCommand(keyword);
    }

    void customClassifierCommand(String keyword) {
//        String keyword = "java";
        CustomClassifier customClassifier = new CustomClassifier();
        ArrayList<SkillCategory> listOfSkillCategories = database.fetchAllSkillCategoryToParse();
        String category = database.parseToJsonCategoryRequest(listOfSkillCategories);
        Response response = customClassifier.execute(category, keyword);
        if (response.isSuccessful()) {
            String responseBodyStringJson = customClassifier.parseResponseToJsonString(response);
            ConfidenceResponse confidenceResponse = new JsonResponseHandler()
                    .jsonToConfidenceResponse(responseBodyStringJson);
            decisionTreeCommand(confidenceResponse, keyword);
        }
    }



    void executeModelCommand(String command) {
        switch (command) {
            case "1": {
                addSkillInBuckets();
                break;
            }
            case "2": {
                updateSkills();
                break;
            }
            default: {
                displayErrorInput();
                command = sc.nextLine();
                executeModelCommand(command);
                break;
            }
        }
    }

    void modelCommand() {
        displayWelcomeModel();
        String command = sc.nextLine();
        executeModelCommand(command);

    }

    //recursive to account for invalid input
    public void executeWelcomeCommand(String command) {
        switch (command) {
            case "1": {
                sthCommand();
                break;
            }
            case "2": {
                modelCommand();
                break;
            }
            default: {
                displayErrorInput();
                command = sc.nextLine();
                executeWelcomeCommand(command);
                break;
            }
        }
    }

    public void welcomeCommand() {
        displayWelcome();
        String command = sc.nextLine();
        executeWelcomeCommand(command);
    }

    public Controller() {
        this.sc = new Scanner(System.in);
        this.database = new Database();
    }


}
