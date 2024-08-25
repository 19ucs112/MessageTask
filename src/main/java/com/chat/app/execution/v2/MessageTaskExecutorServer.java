package com.chat.app.execution.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static com.chat.app.utils.Constants.SERVER_PORT;

/**
 * This class acts as a server and accepts connections with client and runs a ClientHandler thread for each client connection established.
 * This package contains all the implementation related to communication of player object in different java processes.
 */
public class MessageTaskExecutorServer {

    private static final ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clients.add(clientHandler);
            new Thread(clientHandler).start();
        }
    }

    /**
     * This method is responsible for broadcasting messages to all the available clients excluding the sender of the message.
     * @param message
     * @param clientHandler
     */
    private static void broadcast(String message, ClientHandler clientHandler) {
        for (ClientHandler client : clients) {
            if (client != clientHandler) {
                client.sendMessage(message);
            }
        }
    }

    private static class ClientHandler implements Runnable {

        private final BufferedReader bufferedReader;
        private final PrintWriter printWriter;

        public ClientHandler(Socket clientSocket) throws Exception {
            this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        }

        @Override
        public void run() {
            String message;
            while (true) {
                try {
                    if ((message = bufferedReader.readLine()) != null) {
                        System.out.println("Server received: " + message);
                        broadcast(message, this);
                    } else {
                        clients.clear();
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                printWriter.close();
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (clients.isEmpty()) {
                System.exit(0);
            }
        }

        public void sendMessage(String message) {
            printWriter.println(message);
        }
    }
}
