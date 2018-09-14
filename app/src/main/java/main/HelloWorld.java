package main;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloWorld{

    private String API_KEY = "";

    private void run() {
        try {
            Path path = Paths.get("test.txt");
            System.out.println(path.toUri());
            File file = new File(path.toUri());

        }
        catch (Exception e){}
    }

    public static void main(String[] args) {
        HelloWorld myHelloWorld = new HelloWorld();
        myHelloWorld.run();
    }

}