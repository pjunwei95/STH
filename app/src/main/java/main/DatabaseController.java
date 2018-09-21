package main;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class DatabaseController {

    /*
    public static final String text = "java";
    //TODO mongojack to parse json objects from DBs to categories

    public static final String category = "{\"IT\": [\"java\", \"programming\", \"APIs\"]," +
            " \"HR\": [\"human resource\"]}";

    "{"taxonomy":[{"tag":"IT","confidence_score":0.5685330033},
     {"tag":"HR","confidence_score":0.5685330033}],"code":200}";
    */

    public void run() {
        try {

            List<String> itKeywords = Arrays.asList("java", "programming", "APIs");
            List<String> hrKeywords = Arrays.asList("human resource");
            DBObject itCategory = new BasicDBObject("tag","Information Technology")
                    .append("keywords", itKeywords);
            DBObject hrCategory = new BasicDBObject("tag","Human Resource")
                    .append("keywords", hrKeywords);

            MongoClient mongoClient = new MongoClient();
            DB database = mongoClient.getDB("STH");
            DBCollection collection = database.getCollection("skills");

            collection.insert(itCategory, hrCategory);

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
