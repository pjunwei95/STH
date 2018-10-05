package com;

import java.util.ArrayList;

class Bucket {
    private String name;
    private ArrayList<Skill> skills;

    public Bucket(String name) {
        this.name = name;
        this.skills = new ArrayList<>();
    }
}
