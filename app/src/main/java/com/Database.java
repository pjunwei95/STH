package com;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.DBUpdate;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Database {
    //TODO Working on TSC for now, need to implement adding to bucket, then add to industry
    String bucketName = "tsc";

    ArrayList<Skill> fetchAllSkills(JacksonDBCollection<Skill,String> skillColl) {
        ArrayList<Skill> listOfSkills = new ArrayList<>();
        DBCursor<Skill> cursor = skillColl.find();

        while (cursor.hasNext()) {
            Skill skill = cursor.next();
            System.out.println("skillName = " + skill.getName());
            listOfSkills.add(skill);
        }

        return listOfSkills;
    }

    void addKeywordToSkill(String keyword, String skillName) throws UnknownHostException {
        Skill skill = null;
        boolean isInKeyword = false;

        MongoClient mongoClient = new MongoClient();
        DB database = mongoClient.getDB("sth");
        //To build finder
        DBCollection dbCollection = database.getCollection("Skills");

        JacksonDBCollection<Skill, String> skillColl = JacksonDBCollection.wrap(dbCollection, Skill.class,
                String.class);

        DBCursor<Skill> cursor = skillColl.find(DBQuery.is("name",skillName));
        skill = cursor.next();

        if (!skill.equals(null)) {

            ArrayList<String> keywords = skill.getKeywords();
            for (String word : keywords) {
                if (word.equals(keyword)) {
                    isInKeyword = true;
                    break;
                }
            }
            if (!isInKeyword) {
                skillColl.update(DBQuery.is("name", skillName),
                        new DBUpdate.Builder().push("keywords", keyword));
                System.out.println("Updated Successfully!");
            }
            else
                System.out.println("This skill already contains the keyword! Adding failed.");

        }
        else
            System.out.println("Skill not found! Adding failed.");

    }

    public void addSkill(Scanner sc) throws UnknownHostException{
        System.out.println("What skill do you want to add?");
//        String skillName = sc.nextLine();
        String skillName = "Application Integration";

        MongoClient mongoClient = new MongoClient();
        DB database = mongoClient.getDB("sth");
        DBCollection dbCollection = database.getCollection("Skills");

        JacksonDBCollection<Skill, String> coll = JacksonDBCollection.wrap(dbCollection, Skill.class,
                String.class);
        Skill newSkill = new Skill(skillName, bucketName);
        coll.insert(newSkill);
        System.out.println(Message.DATABASE_UPDATE_SUCESS);

    }

    void mongoJackAddSampleData() throws UnknownHostException{

            //Mongodriver, alternative way is using it to add manual DBObject
            MongoClient mongoClient = new MongoClient();
            DB database = mongoClient.getDB("sthbeta");
            DBCollection dbCollection = database.getCollection("IT");

            //MongoJack
            JacksonDBCollection<Skill, String> coll = JacksonDBCollection.wrap(dbCollection, Skill.class,
                    String.class);
            Skill newSkill = new Skill("Application development", "TSC");
            newSkill.addKeywords("blah1");
            newSkill.addKeywords("blah2");
            WriteResult<Skill, String> result = coll.insert(newSkill);
            String id = result.getSavedId();
            System.out.println("id = " +id);
            Skill skill = coll.findOneById(id);
            System.out.println("querying the first keyword: " + skill.getKeywords().get(0));
            System.out.println(Message.DATABASE_UPDATE_SUCESS);

    }

    void run(){

        Scanner sc = new Scanner(System.in);
        try {

            MongoClient mongoClient = new MongoClient();
            DB database = mongoClient.getDB("sth");
            DBCollection dbCollection = database.getCollection("Skills");

            JacksonDBCollection<Skill, String> skillColl = JacksonDBCollection.wrap(dbCollection, Skill.class,
                    String.class);

            System.out.println(Message.UPDATING_MESSAGE);

            //methods

//            addSkill(sc);
//            addKeywordToSkill("blah1", "Application Integration");
            fetchAllSkills(skillColl);

        } catch (UnknownHostException e) {
            System.out.println(Message.ERROR_NETWORK);
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Database database = new Database();
        database.run();
    }
}
