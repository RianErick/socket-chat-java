package org.example;


import java.io.IOException;

public class MainClient {

    public static void main(String[] args) throws IOException {
        Client.connect(8080);
    }
}
