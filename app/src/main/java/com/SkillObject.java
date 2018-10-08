package com;

import java.util.ArrayList;

public class SkillObject {
    private String name;

    public String getName() {
        return name;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    private ArrayList<String> keywords;

    public SkillObject(String name, ArrayList<String> keywords) {
        this.name = name;
        this.keywords = keywords;
    }

}


