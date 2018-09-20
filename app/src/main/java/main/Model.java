package main;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class Model {
    String category;
    ArrayList<String> subcategory;


    /*
    public Model(String category){
        this.category = category;
        this.subcategory = new ArrayList<>();
    }
    */
    public void run() {
        try {
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

//            collection.insert(person);
            collection.remove(person);

        } catch (UnknownHostException e) {
            System.out.println(Message.ERROR_NETWORK);
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Model model = new Model();
        model.run();
    }
}
