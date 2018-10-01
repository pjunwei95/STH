package com;

import java.util.ArrayList;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private String name;
    private ArrayList<String> keywords;
    private String id;

    @JsonProperty("_name")
    public String getName() {
        return name;
    }

    @JsonProperty("_keywords")
    public ArrayList<String> getKeywords() {
        return keywords;
    }


    @ObjectId
    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @ObjectId
    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    public void addKeywords(String keyword) {
        keywords.add(keyword);
    }

    public Skill(){}

    public Skill(String name){
        this.name = name;
        this.keywords = new ArrayList<>();
    }


}
