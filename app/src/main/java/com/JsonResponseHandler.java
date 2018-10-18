package com;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.SkillsTaxonomyHarmoniser.isDebug;
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

            //Pretty print
            String prettyConfidenceResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(confidenceResponse);
            if (isDebug)
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



