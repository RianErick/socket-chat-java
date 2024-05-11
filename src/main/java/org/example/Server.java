package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8080));
        System.out.println("Aguardando Conexao");

        Socket accept = serverSocket.accept();
        System.out.println("Conectado!");

        BufferedReader in = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        PrintWriter out = new PrintWriter(accept.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);

        new ServidorHandler(accept, in, out).start();
        String msg = scanner.nextLine();

        while (!msg.equals("exit")) {
            out.println(msg);
            out.flush();
            msg = scanner.nextLine();
        }
    }
    static class ServidorHandler extends Thread {

        Socket socket;
        BufferedReader in;
        PrintWriter out;

        public ServidorHandler(Socket socket, BufferedReader in, PrintWriter out) {
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
