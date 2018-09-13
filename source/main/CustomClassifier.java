package main;

import java.net.UnknownHostException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CustomClassifier {

    private String API_KEY = ""; //V5lSS0MB
    
    public static final String text = "java";
    //TODO mongojack to parse json objects from DBs to categories
    public static final String category = "{\"IT\": [\"java\", \"programming\", \"APIs\"]," +
            " \"HR\": [\"human resource\"]}";

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
            System.out.println(response);

            if (response.code() == 200) {
                System.out.println("RESPONSE SUCCESS! RESPONSE:");
                byte[] responseBodyByte = response.body().bytes();
                String responseBodyString = new String(responseBodyByte, "UTF-8");
                System.out.println(responseBodyString);



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