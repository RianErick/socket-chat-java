package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void connect(int port) throws IOException {

        Socket socket = new Socket("localhost", port);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);

        new ClientHandler(socket,in,out).start();
        String msg = scanner.nextLine();

        while (!msg.equals("exit")) {
            out.println(msg);
            out.flush();
            msg = scanner.nextLine();
        }
    }

    static class ClientHandler extends Thread {

        Socket socket;
        BufferedReader in;
        PrintWriter out;

        public ClientHandler(Socket socket, BufferedReader in, PrintWriter out) {
            this.socket = socket;
            this.in = in;
            this.out = out;
        }

        @Override
        public void run() {
            while (true) {
            try {
                  String messageFromServer = in.readLine();

                    if (messageFromServer != null) {
                        System.out.println(messageFromServer);

                        if ("exit".equalsIgnoreCase(messageFromServer)) {
                            in.close();
                            out.close();
                            socket.close();
                            break;
                        }
                    }
                } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }
    }
}
