package main;

import java.net.UnknownHostException;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.util.JSONPObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CustomClassifier {

    private String API_KEY = ReadApiKey.getAPI_KEY();
    
    public static final String text = "java";
    //TODO mongojack to parse json objects from DBs to categories
    //TODO Process Json to strings by getting top response, delimited with their confidences

    public static final String category = "{\"IT\": [\"java\", \"programming\", \"APIs\"]," +
            " \"HR\": [\"human resource\"]}";

    //"{"taxonomy":[{"tag":"IT","confidence_score":0.5685330033},{"tag":"HR","confidence_score":0.5685330033}],"code":200}";

    public void run() {

        try {
            String url = "https://apis.paralleldots.com/v3/custom_classifier";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
                    .url(url + "?api_key=" + API_KEY + "&text=" + text + "&category=" + category)
                    .post(body)
                    .addHeader("cache-control", "no-cache")
                    .build();
            Response response = client.newCall(request).execute();
//            System.out.println("Respone: " + response);
//            System.out.println("ResponseBodyToString: " + response.body().toString());

            if (response.code() == 200) {
                byte[] responseBodyByte = response.body().bytes();
                String responseBodyString = new String(responseBodyByte, "UTF-8");
                System.out.println("ResponseBodyString: "+ responseBodyString);

                JSONObject jsonObject = new JSONObject(response.body().string());
            }


        } catch (UnknownHostException e) {
            System.out.println(Message.ERROR_NETWORK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CustomClassifier myCustomClassifier = new CustomClassifier();
        myCustomClassifier.run();
    }

}