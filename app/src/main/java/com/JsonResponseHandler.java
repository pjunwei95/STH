package com;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Response;


public class JsonResponseHandler {


    public ConfidenceResponse jsonToConfidenceResponse(String jsonInString) {

        ObjectMapper mapper = new ObjectMapper();
        ConfidenceResponse confidenceResponse = null;

        try {

            // Convert JSON string to Object
            confidenceResponse = mapper.readValue(jsonInString, ConfidenceResponse.class);
            confidenceResponse.sortbyHighest();
//            System.out.println(confidenceResponse);

//            System.out.println("Testing: ");
//            System.out.println("Getting value of 2nd tag: " +
//                    confidenceResponse.getTaxonomy().get(1).getTag());

            //Pretty print
            String prettyConfidenceResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(confidenceResponse);
            System.out.println(prettyConfidenceResponse);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return confidenceResponse;
        }
    }

    public static void main(String[] args) {
//        JsonResponseHandler jsonResponseHandler = new JsonResponseHandler();
//        jsonResponseHandler.jsonToConfidenceResponse();
    }

}
class ConfidenceResponse{
    private List<Taxonomy> taxonomy;
    private int code;

        public void sortbyHighest() {
        Collections.sort(taxonomy, Collections.reverseOrder());
    }

    public List<Taxonomy> getTaxonomy() {
        return taxonomy;
    }

//    public void setTaxonomy(List<Taxonomy> taxonomy) {
//        this.taxonomy = taxonomy;
//    }

    public int getCode() {
        return code;
    }

//    public void setCode(int code) {
//        this.code = code;
//    }
}

class Taxonomy implements Comparable<Taxonomy>{
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

//    public void setTag(String tag) {
//        this.tag = tag;
//    }

    public BigDecimal getConfidence_score() {
        return confidence_score;
    }

//    public void setConfidence_score(BigDecimal confidence_score) {
//        this.confidence_score = confidence_score;
//    }
}
