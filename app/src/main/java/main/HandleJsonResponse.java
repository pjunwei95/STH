package main;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

/*String jsonInString = "{\"taxonomy\":[{\"tag\":\"IT\",\"confidence_score\":0.5685330033}," +
                    " {\"tag\":\"HR\",\"confidence_score\":0.5685330033}],\"code\":200}";
                    */

public class HandleJsonResponse {

    void run() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = "{\"name\" : \"mkyong\"}";
            //JSON from String to Object
            Staff obj = mapper.readValue(jsonInString, Staff.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HandleJsonResponse handleJsonResponse = new HandleJsonResponse();
        handleJsonResponse.run();
    }

}
//"{"taxonomy":[{"tag":"IT","confidence_score":0.5685330033},
// {"tag":"HR","confidence_score":0.5685330033}],"code":200}";

class Staff {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    private int age;
    private String position;
    private BigDecimal salary;
    private List<String> skills;
}

class ConfidenceResponse{
    ArrayList<Taxonomy> taxonomy;
    int code;

    public ConfidenceResponse(){
        this.taxonomy = new ArrayList<>();
    }

}

class Taxonomy{
    String tag;
    double confidenceScore;
}
