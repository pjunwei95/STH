package com;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.DBUpdate;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import static com.SkillsTaxonomyHarmoniser.isDebug;

public class Database {
    //TODO Working on TSC for now, need to implement adding to bucket, then add to industry
    String bucketName = "tsc";
    MongoClient mongoClient;
    DB database;
    DBCollection dbCollection;
    JacksonDBCollection<Skill, String> skillColl;
    Scanner sc;

    public static void main(String[] args) {
        Database database = new Database();
        database.run();
    }

    void run(){
        try {
            //methods
            addSkill(sc);
//            addKeywordToSkill("hammering", "Engineering");
//            addKeywordToSkill("screws", "Engineering");
//            ArrayList<SkillObject> listOfSkillObjects = fetchAllSkills(skillColl);
//            String category = getCategoryRequest(listOfSkillObjects);
//            CustomClassifier customClassifier = new CustomClassifier();
//            customClassifier.jsonToConfidenceResponse(category);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    void addKeywordToNoise(String keyword) {

    }


    public JacksonDBCollection<Skill, String> getSkillColl() {
        System.out.println(Message.UPDATING_MESSAGE);

        try {
            mongoClient = new MongoClient();
            database = mongoClient.getDB("sth");
            //To build finder
            dbCollection = database.getCollection("Skills");

            return skillColl = JacksonDBCollection.wrap(dbCollection, Skill.class,
                    String.class);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCategoryRequest(ArrayList<SkillObject> listOfSkillObjects) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");
        for (int i=0;i<listOfSkillObjects.size();i++) {
            stringBuilder.append("\"");
            stringBuilder.append(listOfSkillObjects.get(i).getName());
            stringBuilder.append("\"");
            stringBuilder.append(": [");
            for (int j=0;j<listOfSkillObjects.get(i).getKeywords().size();j++) {
                stringBuilder.append("\"");
                stringBuilder.append(listOfSkillObjects.get(i).getKeywords().get(0));
                stringBuilder.append("\"");
                if (j!=listOfSkillObjects.get(i).getKeywords().size()-1)
                    stringBuilder.append(",");
            }

            stringBuilder.append("]");
            if (i!=listOfSkillObjects.size()-1)
                stringBuilder.append(",");
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    public ArrayList<SkillObject> fetchAllSkills(JacksonDBCollection<Skill,String> skillColl) {
        ArrayList<SkillObject> listOfSkillObjects = new ArrayList<>();
        DBCursor<Skill> cursor = skillColl.find();

        while (cursor.hasNext()) {
            Skill skill = cursor.next();
            SkillObject skillObject = new SkillObject(skill.getName(),skill.getKeywords());
//            System.out.println("skillName = " + skill.getName());
            listOfSkillObjects.add(skillObject);
        }

        return listOfSkillObjects;
    }

    void addKeywordToSkill(String keyword, String skillName) {
        Skill skill = null;
        boolean isInKeyword = false;

        try {
            DBCursor<Skill> cursor = skillColl.find(DBQuery.is("name", skillName));
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
                    System.out.printf("Keyword [%s] has been added to the skill [%s]!\n", keyword, skillName);

                } else
                    System.out.println("This skill already contains the keyword! Adding failed.");

            }
        } catch (NoSuchElementException e) {
            System.out.println("Skill not found! Adding failed.");
            if (isDebug)
                e.printStackTrace();
        }

    }

    public void addSkill(Scanner sc) throws UnknownHostException{
        System.out.println("What skill do you want to add?");
        String skillName = sc.nextLine();
//        String skillName = "testMultiple";

        Skill skill = skillColl.findOne(DBQuery.is("name",skillName));
        if (skill != null) {
            System.out.println("Already has skill with name: " + skillName);
            System.out.println("Adding failed!");
            return;
        }

        Skill newSkill = new Skill(skillName, bucketName);
        skillColl.insert(newSkill);
        System.out.println(Message.DATABASE_UPDATE_SUCESS);
    }


    public Database(){
        sc = new Scanner(System.in);

        try {
            mongoClient = new MongoClient();
            database = mongoClient.getDB("sth");
            //To build finder
            dbCollection = database.getCollection("Skills");

            skillColl = JacksonDBCollection.wrap(dbCollection, Skill.class,
                    String.class);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    //Deprecated
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

}
