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
    MongoClient mongoClient;
    DB database;
    DBCollection skillDbCollection;
    DBCollection noiseDbCollection;
    JacksonDBCollection<Skill, String> skillColl;
    JacksonDBCollection<Skill, String> noiseColl;
    Scanner sc;

    public ArrayList<Skill> fetchAllSkills() {
        ArrayList<Skill> listOfSkills = new ArrayList<>();
        DBCursor<Skill> cursor = skillColl.find();

        while (cursor.hasNext()) {
            Skill skill = cursor.next();
            listOfSkills.add(skill);
        }

        return listOfSkills;
    }

    void removeKeywordFromSkill(String keyword, String skillName) {
        Skill skill = null;
        try {
            DBCursor<Skill> cursor = skillColl.find(DBQuery.is("name", skillName));
            skill = cursor.next();

            if (!skill.equals(null)) {
                skillColl.update(DBQuery.is("name", skillName),
                            new DBUpdate.Builder().pull("keywords", keyword));
                System.out.println("Updated Successfully!");
                System.out.printf("Keyword [%s] has been removed from the skill [%s]!\n", keyword, skillName);
            } else
                System.out.println("This skill does not contain the keyword"); //should not happen

        } catch (NoSuchElementException e) {
            System.out.println("Skill not found! Adding failed.");
            if (isDebug)
                e.printStackTrace();
        }
    }


    void addKeywordToNoise(String keyword) {
            Skill skill = noiseColl.findOne(DBQuery.is("name", keyword));
            if (skill != null) {
                System.out.println("Already has noise with name: " + keyword);
                System.out.println("Adding failed!");
                return;
            }

            Skill newNoise = new Skill(keyword, "Noise");
            noiseColl.insert(newNoise);
            System.out.println(Message.DATABASE_UPDATE_SUCESS);
            System.out.printf("Keyword [%s] has been added to [Noise]!\n", keyword);

    }


    public ArrayList<Skill> fetchAllSkillsWithKeyword(String keyword) {
        ArrayList<Skill> listOfSkill = new ArrayList<>();
        DBCursor<Skill> cursor = skillColl.find().in("keywords", keyword);

        while (cursor.hasNext()) {
            Skill skill = cursor.next();
//            System.out.println("skillName = " + skill.getName());
            listOfSkill.add(skill);
        }

        return listOfSkill;
    }

    public JacksonDBCollection<Skill, String> getJackSkillColl() {
        System.out.println(Message.UPDATING_MESSAGE);

        try {
            mongoClient = new MongoClient();
            database = mongoClient.getDB("sth");
            //To build finder
            skillDbCollection = database.getCollection("Skills");

            return skillColl = JacksonDBCollection.wrap(skillDbCollection, Skill.class,
                    String.class);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String parseToJsonCategoryRequest(ArrayList<SkillCategory> listOfSkillCategories) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");
        for (int i = 0; i< listOfSkillCategories.size(); i++) {
            stringBuilder.append("\"");
            stringBuilder.append(listOfSkillCategories.get(i).getName());
            stringBuilder.append("\"");
            stringBuilder.append(": [");
            for (int j = 0; j< listOfSkillCategories.get(i).getKeywords().size(); j++) {
                stringBuilder.append("\"");
                stringBuilder.append(listOfSkillCategories.get(i).getKeywords().get(0));
                stringBuilder.append("\"");
                if (j!= listOfSkillCategories.get(i).getKeywords().size()-1)
                    stringBuilder.append(",");
            }

            stringBuilder.append("]");
            if (i!= listOfSkillCategories.size()-1)
                stringBuilder.append(",");
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    public ArrayList<SkillCategory> fetchAllSkillCategoryToParse() {
        ArrayList<SkillCategory> listOfSkillCategories = new ArrayList<>();
        DBCursor<Skill> cursor = skillColl.find();

        while (cursor.hasNext()) {
            Skill skill = cursor.next();
            SkillCategory skillCategory = new SkillCategory(skill.getName(),skill.getKeywords());
//            System.out.println("skillName = " + skill.getName());
            listOfSkillCategories.add(skillCategory);
        }

        return listOfSkillCategories;
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

    public void addSkill(Scanner sc, String bucketName){
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
            skillDbCollection = database.getCollection("Skills");
            noiseDbCollection = database.getCollection("Noise");

            skillColl = JacksonDBCollection.wrap(skillDbCollection, Skill.class,
                    String.class);

            noiseColl = JacksonDBCollection.wrap(noiseDbCollection, Skill.class,
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
