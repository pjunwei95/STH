package com;

public class HelloWorld{

    void print() {
        System.out.println("Hello World!");
    }

    private void run() {}

    public static void main(String[] args) {
        HelloWorld myHelloWorld = new HelloWorld();
        myHelloWorld.run();
    }
}