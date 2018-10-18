package com;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.view.Message;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ReadApiKey {

    private static String readApiFromFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static String getAPI_KEY() {
        try {
            String API_KEY = readApiFromFile("api_key.txt", UTF_8);
            return API_KEY;

        } catch (IOException e) {
            System.out.println(Message.ERROR_FILE);
            e.printStackTrace();
            return "";
        }
    }
}