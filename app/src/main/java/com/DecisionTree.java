package com;

import java.util.List;

import com.model.ConfidenceResponse;
import com.model.Taxonomy;

public class DecisionTree {

    public static final double CONFIDENCE_UPPER_LIMIT = 0.75;
    public static final double CONFIDENCE_LOWER_LIMIT = 0.25;
    public static final double CONFIDENCE_SIMILARITY_INDEX = 0.10;

    public boolean isSimilar(double a, double b, double c) {
        if (Math.abs(a-b)<CONFIDENCE_SIMILARITY_INDEX ||
                Math.abs(a-c)<CONFIDENCE_SIMILARITY_INDEX ||
                Math.abs(b-c)<CONFIDENCE_SIMILARITY_INDEX)
            return true;
        return false;
    }

    public Taxonomy[] execute(ConfidenceResponse confidenceResponse){
        Taxonomy[] taxonomyArray = new Taxonomy[3];
        List<Taxonomy> taxonomyList = confidenceResponse.getTaxonomy();
        if (taxonomyList.size() < 3) {
            System.out.println("Error! Taxonomies does not meet minimum requirement");
            return null;
        }
        Taxonomy firstTaxonomy = taxonomyList.get(0);
        Taxonomy secondTaxonomy = taxonomyList.get(1);
        Taxonomy thirdTaxonomy = taxonomyList.get(2);

        taxonomyArray[0] = firstTaxonomy;
        taxonomyArray[1] = secondTaxonomy;
        taxonomyArray[2] = thirdTaxonomy;

        return taxonomyArray;
    }
}
