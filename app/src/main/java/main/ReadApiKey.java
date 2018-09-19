package main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ReadApiKey {

    private static String readApiFromFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static String getAPI_KEY() {
        try {
//            Path path = Paths.get("api_key.txt");
//            System.out.println(path.toUri());
//            File file = new File(path.toUri());

            String API_KEY = readApiFromFile("api_key.txt", UTF_8);
//            System.out.println(API_KEY);
            return API_KEY;

        } catch (IOException e) {
            System.out.println(Message.ERROR_FILE);
            e.printStackTrace();
            return "";
        }
    }
}