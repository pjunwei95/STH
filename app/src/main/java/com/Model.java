package com;

import java.util.ArrayList;

public class Model {
    private String name;
    private ArrayList<Bucket> buckets;

    public Model(String name) {
        this.name = name;
        this.buckets = new ArrayList<>();
    }
}

class Bucket {
    private String name;
    private ArrayList<Skill> skills;

    public Bucket(String name) {
        this.name = name;
        this.skills = new ArrayList<>();
    }
}


class Skill {
    private String category;
    private ArrayList<String> keywords;

    public Skill(String category){
        this.category = category;
        this.keywords = new ArrayList<>();
    }


}
