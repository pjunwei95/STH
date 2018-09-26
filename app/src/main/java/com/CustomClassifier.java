package com;

import java.net.UnknownHostException;

import static com.SkillsTaxonomyHarmoniser.isApiKeyEnabled;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CustomClassifier {

    private static String API_KEY = ReadApiKey.getAPI_KEY();

    public static final String text = "java";

    public static final String category = "{\"IT\": [\"java\", \"programming\", \"APIs\"]," +
            " \"HR\": [\"human resource\"]}";

    //"{"taxonomy":[{"tag":"IT","confidence_score":0.5685330033},
    // {"tag":"HR","confidence_score":0.5685330033}],"code":200}";


    public void parseResponse(Response response) {
        if (!isApiKeyEnabled)
            API_KEY = "";

        try {
            byte[] responseBodyByte = response.body().bytes();
            String responseBodyStringJson = new String(responseBodyByte, "UTF-8");
            System.out.println("ResponseBodyStringJson: " + responseBodyStringJson);
            System.out.println(Message.BORDER);
            new JsonResponseHandler().run(responseBodyStringJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            System.out.println("Response: " + response);
            System.out.println(Message.BORDER);
//            System.out.println("Response.body().toString(): " + response.body().toString());
//            System.out.println(Message.BORDER);


            if (response.isSuccessful()) {
                parseResponse(response);
            }


        } catch (UnknownHostException e) {
            System.out.println(Message.ERROR_NETWORK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Deprecated
    public String convertJsonStringtoJavaString(String string) {
        return string.replace("\"", "\\\"");
    }

    public static void main(String[] args) {
        CustomClassifier myCustomClassifier = new CustomClassifier();
        myCustomClassifier.run();
    }

}
