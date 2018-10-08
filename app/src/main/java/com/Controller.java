package com;

import java.util.ArrayList;
import java.util.Scanner;

import static com.DecisionTree.CONFIDENCE_LOWER_LIMIT;
import static com.DecisionTree.CONFIDENCE_UPPER_LIMIT;
import static com.View.*;
import okhttp3.Response;

public class Controller {
    Scanner sc;
    CustomClassifier customClassifier;
    Database database;
    DecisionTree decisionTree;

    public static void main(String args[]) {
        Controller controller = new Controller();
    }

    void prompt2(){}

    void similarPromptCommand(Taxonomy[] taxonomyArray, String keyword){
        Taxonomy firstTaxonomy = taxonomyArray[0];
        Taxonomy secondTaxonomy = taxonomyArray[1];
        Taxonomy thirdTaxonomy = taxonomyArray[2];
        displaySimilarPrompt(taxonomyArray, keyword);
        int command = sc.nextInt();
        switch (command) {
            case 1:{
                database.addKeywordToSkill(keyword, firstTaxonomy.getTag());
                break;
            }
            case 2:{
                database.addKeywordToSkill(keyword, secondTaxonomy.getTag());
                break;
            }
            case 3:{
                database.addKeywordToSkill(keyword, thirdTaxonomy.getTag());
                break;
            }
            default:
                break;
        }

    }

    void decisionTreeCommand(ConfidenceResponse confidenceResponse, String keyword){
        Taxonomy[] taxonomyArray = decisionTree.execute(confidenceResponse);
        Taxonomy firstTaxonomy = taxonomyArray[0];
        Taxonomy secondTaxonomy = taxonomyArray[1];
        Taxonomy thirdTaxonomy = taxonomyArray[2];

        if (decisionTree.isSimilar(firstTaxonomy.getConfidence_score().doubleValue(),
                secondTaxonomy.getConfidence_score().doubleValue(),
                thirdTaxonomy.getConfidence_score().doubleValue()))
            similarPromptCommand(taxonomyArray, keyword);
        else if (firstTaxonomy.getConfidence_score().doubleValue() > CONFIDENCE_UPPER_LIMIT)
            database.addKeywordToSkill(keyword, firstTaxonomy.getTag());
        else if (firstTaxonomy.getConfidence_score().doubleValue() < CONFIDENCE_LOWER_LIMIT)
            database.addKeywordToNoise(keyword);
        else
            prompt2();

    }


    void sthCommand() {
        System.out.println("What keyword do you want to add to the skill framework?");
        String keyword = sc.nextLine();
        //TODO search whether its already inside the keywords

        customClassifierCommand(keyword);
    }

    void customClassifierCommand(String keyword) {
//        String keyword = "java";
        ArrayList<SkillObject> listOfSkillObjects = database.fetchAllSkills(database.getSkillColl());
        String category = database.getCategoryRequest(listOfSkillObjects);
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
                displayUnderDevelopment();
                break;
            }
            case "2": {
                System.out.println("Which model would you like to update?");
                ArrayList<String> models = new ArrayList<>();
                String category = sc.nextLine();
                System.out.println("Category not available");
                displayUnderDevelopment();
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
        this.customClassifier = new CustomClassifier();
        this.database = new Database();
        this.decisionTree = new DecisionTree();
    }


}
