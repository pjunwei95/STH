package main;

import java.util.*;

//        Scoping of ict and skills from linkedin list of skills ad cope it out
//        Brain could be vendors, real world cannnot have scope on some things
//        Mongodb query
//        Front-end framework for java->business users CLI isfor developers
//        Free tier hosting: heroku, aws, RESTFUL for frontend
//        Containers that listen to https calls
//        CLI is more for developers

public class STH {

    void run () {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to the Skills Taxonomy Harmoniser!");
            System.out.println("Functions: ");
            System.out.println("    1. New/Update Model");
            System.out.println("    2. Harmonise the skills");
            System.out.println("Please select the options(no.) which you would like to use:");
            String function = sc.next();
            CustomClassifier customClassifier;
            if (function.equals("1")) {
                customClassifier = new CustomClassifier();
                customClassifier.run();
            }
        }
    }

    public static void main(String[] args) {
        STH mySTH = new STH();
        mySTH.run();
    }
}
