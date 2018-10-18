package com.model;

import java.util.Collections;
import java.util.List;

public class ConfidenceResponse{
    private List<Taxonomy> taxonomy;
    private int code;

    public void sortbyHighest() {
        Collections.sort(taxonomy, Collections.reverseOrder());
    }

    public List<Taxonomy> getTaxonomy() {
        return taxonomy;
    }

    public int getCode() {
        return code;
    }

}