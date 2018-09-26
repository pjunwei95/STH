package com;

import java.util.ArrayList;

public class Model {
    private String category;
    private ArrayList<String> subcategory;

    public Model(String category){
        this.category = category;
        this.subcategory = new ArrayList<>();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(ArrayList<String> subcategory) {
        this.subcategory = subcategory;
    }
}
