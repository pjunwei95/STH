package com;


import java.net.UnknownHostException;
import java.util.ArrayList;

import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

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


    void mongoJackAddNestedData(String id) throws UnknownHostException{
        MongoClient mongoClient = new MongoClient();
        DB database = mongoClient.getDB("sth2");
        DBCollection dbCollection = database.getCollection("IT");
        JacksonDBCollection<Bucket, String> coll = JacksonDBCollection.wrap(dbCollection, Bucket.class,
                String.class);
        Bucket newBucket = new Bucket("TSC");


    }

    void mongoJackAddSampleData() throws UnknownHostException{
            MongoClient mongoClient = new MongoClient();
            DB database = mongoClient.getDB("sth2");
            DBCollection dbCollection = database.getCollection("IT");

            JacksonDBCollection<Skill, String> coll = JacksonDBCollection.wrap(dbCollection, Skill.class,
                    String.class);
            Skill newSkill = new Skill("Application development");
            newSkill.addKeywords("blah1");
            newSkill.addKeywords("blah2");
            WriteResult<Skill, String> result = coll.insert(newSkill);
            String id = result.getSavedId();
            System.out.println("id = " +id);
            Skill skill = coll.findOneById(id);
            System.out.println("querying the first keyword: " + skill.getKeywords().get(0));
            mongoJackAddNestedData(id);

    }

    public void driverAddSampleData() throws UnknownHostException{
        ArrayList<DBObject> listOfSkills = new ArrayList<>();
        ArrayList<DBObject> listOfBuckets = new ArrayList<>();
        ArrayList<String> listOfKeywords = new ArrayList<>();

            listOfKeywords.add("blah1");
            listOfKeywords.add("blah2");

            DBObject appDevelopmentSkills = new BasicDBObject("skillName", "appDevelopment")
                    .append("keywords", listOfKeywords);
            listOfSkills.add(appDevelopmentSkills);
            DBObject tscBucket = new BasicDBObject("bucketName","tsc")
                    .append("listOfSkills", listOfSkills);
            listOfBuckets.add(tscBucket);
            DBObject data = new BasicDBObject("listOfBuckets", listOfBuckets);

            MongoClient mongoClient = new MongoClient();
            DB database = mongoClient.getDB("sth");
            DBCollection collection = database.getCollection("IT");
            collection.insert(data);

    }

    void run(){

        System.out.println(Message.UPDATING_MESSAGE);

        try {
            mongoJackAddSampleData();
        } catch (UnknownHostException e) {
            System.out.println(Message.ERROR_NETWORK);
            e.printStackTrace();
        }

        System.out.println(Message.DATABASE_UPDATE_SUCESS);

    }

    public static void main(String[] args) {
        DatabaseController databaseController = new DatabaseController();
        databaseController.run();
    }
}
