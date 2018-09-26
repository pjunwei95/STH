package com;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DatabaseController {

    /*
    public static final String text = "java";

    public static final String category = "{\"IT\": [\"java\", \"programming\", \"APIs\"]," +
            " \"HR\": [\"human resource\"]}";

    "{"taxonomy":[{"tag":"IT","confidence_score":0.5685330033},
     {"tag":"HR","confidence_score":0.5685330033}],"code":200}";
    */

    //MongoDB Structure
    //DB: STH, Industry: IT

    //Industry{
    //name: string (IT)
    //categories: AL String/Category

    //Category
    //name : string (TSC)
    //skills: AL String/skill

    //Skills
    //name: string (App Development)
    //keywords: AL String


    public void run() {
        try {

            ArrayList<DBObject> listOfSkills = new ArrayList<>();
            ArrayList<DBObject> listOfBuckets = new ArrayList<>();

            List<String> keywords = Arrays.asList("blah", "blah2");

            DBObject appDevelopmentSkills = new BasicDBObject("skillName", "appDevelopment")
                    .append("keywords", keywords);
            listOfSkills.add(appDevelopmentSkills);
            DBObject tscBucket = new BasicDBObject("bucketName","tsc")
                    .append("listOfSkills", listOfSkills);
            listOfBuckets.add(tscBucket);
            DBObject data = new BasicDBObject("listOfBuckets", listOfBuckets);

            MongoClient mongoClient = new MongoClient();
            DB database = mongoClient.getDB("sth");
            DBCollection collection = database.getCollection("IT");
            collection.insert(data);


            /*
            List<Integer> books = Arrays.asList(27464, 747854);
            DBObject person = new BasicDBObject("_id", "jo")
                    .append("name", "Jo Bloggs")
                    .append("address", new BasicDBObject("street", "123 Fake St")
                            .append("city", "Faketon")
                            .append("state", "MA")
                            .append("zip", 12345))
                    .append("books", books);



            MongoClient mongoClient = new MongoClient();
            DB database = mongoClient.getDB("Examples");
            DBCollection collection = database.getCollection("people");
            collection.insert(person);
            collection.remove(person);
*/

        } catch (UnknownHostException e) {
            System.out.println(Message.ERROR_NETWORK);
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        DatabaseController databaseController = new DatabaseController();
        databaseController.run();
    }
}
