package com;

import java.math.BigDecimal;

public class Taxonomy implements Comparable<Taxonomy>{
    private String tag;
    private BigDecimal confidence_score;
    //Underscore naming due to conventions of the result of ParallelDots API

    @Override
    public int compareTo(Taxonomy other) {
        return this.getConfidence_score().compareTo(other.getConfidence_score());
    }

    public String getTag() {
        return tag;
    }

    public BigDecimal getConfidence_score() {
        return confidence_score;
    }

}
