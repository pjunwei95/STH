package main;

import java.util.ArrayList;

public class Model {
    String category;
    ArrayList<String> subcategory;

    public Model(String category){
        this.category = category;
        this.subcategory = new ArrayList<>();
    }

}
