package main;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonResponseHandler {

//    private String jsonInString = "{\"taxonomy\":[{\"tag\":\"IT\",\"confidence_score\":0.5685330033}," +
//            " {\"tag\":\"HR\",\"confidence_score\":0.5685330033}],\"code\":200}";

    void run(String jsonInString) {

        ObjectMapper mapper = new ObjectMapper();

        try {

            // Convert JSON string to Object
            ConfidenceResponse confidenceResponse = mapper.readValue(jsonInString, ConfidenceResponse.class);
            System.out.println(confidenceResponse);

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
        }
    }

    public static void main(String[] args) {
        JsonResponseHandler jsonResponseHandler = new JsonResponseHandler();
//        jsonResponseHandler.run();
    }

}
class ConfidenceResponse{
    private List<Taxonomy> taxonomy;
    private int code;

    public List<Taxonomy> getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(List<Taxonomy> taxonomy) {
        this.taxonomy = taxonomy;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

class Taxonomy{
    private String tag;
    private BigDecimal confidence_score;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public BigDecimal getConfidence_score() {
        return confidence_score;
    }

    public void setConfidence_score(BigDecimal confidence_score) {
        this.confidence_score = confidence_score;
    }
}
