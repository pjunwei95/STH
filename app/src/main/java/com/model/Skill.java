package com.model;

import java.util.ArrayList;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Skill {
    private String _id;
    private String name;
    private ArrayList<String> keywords;
    private String bucketName;


    public Skill(){}

    public Skill(String name, String bucket) {
        this.name = name;
        this.bucketName = bucket;
        this.keywords = new ArrayList<>();
    }

    public void addKeywords(String keyword) {
        keywords.add(keyword);
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("bucketName")
    public String getBucketName() {
        return bucketName;
    }

    @JsonProperty("keywords")
    public ArrayList<String> getKeywords() {
        return keywords;
    }


    @ObjectId
    @JsonProperty("_id")
    public String getId() {
        return _id;
    }

    @ObjectId
    @JsonProperty("_id")
    public void setId(String id) {
        this._id = id;
    }



}