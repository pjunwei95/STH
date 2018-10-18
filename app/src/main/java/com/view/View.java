package com.view;

import com.model.Taxonomy;

public class View {

    public static void displayChooseBuckets() {
        System.out.println("Which buckets do you want to access?");
        System.out.println("    1. TSC");
        System.out.println("    2. GSC");
        System.out.println("    3. Noise");
    }

    public static void displaySimilarPrompt(Taxonomy[] taxonomyArray, String keyword) {
        Taxonomy firstTaxonomy = taxonomyArray[0];
        Taxonomy secondTaxonomy = taxonomyArray[1];
        Taxonomy thirdTaxonomy = taxonomyArray[2];
        System.out.printf("The top 3 confidence levels for [%s] are similar for the following taxonomies:\n", keyword);
        System.out.printf("1. %s = %.3f\n", firstTaxonomy.getTag(), firstTaxonomy.getConfidence_score().doubleValue());
        System.out.printf("2. %s = %.3f\n", secondTaxonomy.getTag(), secondTaxonomy.getConfidence_score().doubleValue());
        System.out.printf("3. %s = %.3f\n", thirdTaxonomy.getTag(), thirdTaxonomy.getConfidence_score().doubleValue());
        System.out.println("Which skill do you want to place it in?");
        System.out.println("Type 4=Noise or 5=Cancel");
    }

    public static void displayWelcomeModel() {
        System.out.println("You are accessing the Skills Framework Database: [Infocomm Technology]");
        System.out.println("What would you like to do?");
        System.out.println("    1. Create new skill");
        System.out.println("    2. View/Update existing skills");
    }

    public static void displayErrorInput() {
        System.out.println(Message.ERROR_INPUT);
        System.out.println(Message.INSTRUCTIONS);
    }

    public static void displayWelcome() {
        System.out.println(Message.BORDER);
        System.out.println("Functions: ");
        System.out.println("    1. Harmonise the skills");
        System.out.println("    2. Modify Skills Framework Database");
        System.out.println(Message.INSTRUCTIONS);
    }

    public static void displayUnderDevelopment() {
        System.out.println(Message.DISPLAY_DEVELOPMENT.trim());
        System.out.println("Exiting...");
        System.out.println(Message.BORDER);
        System.out.println("Going back to main menu");
    }

}
