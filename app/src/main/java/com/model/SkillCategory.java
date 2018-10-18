package com.model;

import java.util.ArrayList;

public class SkillCategory {
    private String name;

    public String getName() {
        return name;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    private ArrayList<String> keywords;

    public SkillCategory(String name, ArrayList<String> keywords) {
        this.name = name;
        this.keywords = keywords;
    }

}


