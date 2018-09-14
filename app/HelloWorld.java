import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


public class HelloWorld{

    private String API_KEY = "";
    String text = "For the Yankees, it took a stunning comeback" +
            " after being down 2-0 to the Indians in the American League Division Series." +
            " For the Astros, it took beating Chris Sale to top the Red Sox.";
    private void run() {
        System.out.println("Hello STH World!");

        //This is a test for the taxonomy API which was discontinued

/*
        try {
            String url = "https://apis.paralleldots.com/v3/taxonomy";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
                    .url(url+"?api_key="+API_KEY+"&text="+text)
                    .post(body)
                    .addHeader("cache-control", "no-cache")
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response);

            if (response.code() == 200) {
                byte[] responseBodyByte = response.body().bytes();
                String responseBodyString = new String(responseBodyByte, "UTF-8");
                System.out.println(responseBodyString);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }

    public static void main(String[] args) {
        HelloWorld myHelloWorld = new HelloWorld();
        myHelloWorld.run();
    }

}