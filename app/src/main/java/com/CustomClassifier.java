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

    public String parseResponseToJsonString(Response response) {
        String responseBodyStringJson = null;
        try {
            byte[] responseBodyByte = response.body().bytes();
            responseBodyStringJson = new String(responseBodyByte, "UTF-8");
//            System.out.println("ResponseBodyStringJson: " + responseBodyStringJson);
            System.out.println(Message.BORDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseBodyStringJson;
    }

    public Response execute(String category, String text) {
        Response response = null;

        if (!isApiKeyEnabled) {
            API_KEY = "";
            System.out.println("API_KEY Disabled");
        }

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
            response = client.newCall(request).execute();
//            System.out.println("Response: " + response);
//            System.out.println(Message.BORDER);
//            System.out.println("Response.body().toString(): " + response.body().toString());
//            System.out.println(Message.BORDER);

        } catch (UnknownHostException e) {
            System.out.println(Message.ERROR_NETWORK);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return response;
        }
    }

    // Deprecated
    public String convertJsonStringtoJavaString(String string) {
        return string.replace("\"", "\\\"");
    }

}
