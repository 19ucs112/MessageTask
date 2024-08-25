package com.chat.app.execution.v2;

import com.chat.app.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.chat.app.utils.Constants.INITIATOR;
import static com.chat.app.utils.Constants.MAX_MESSAGE_COUNT;
import static com.chat.app.utils.Constants.SERVER_HOST;
import static com.chat.app.utils.Constants.SERVER_PORT;

/**
 * <p>
 * MessageTaskExecutionClient is responsible for making connection with the ExecutionServer, sending and receiving
 * messages from the server. Initializing as many instances of this ExecutorClient makes those many connections
 * with the server.
 * .</p>
 */
public class MessageTaskExecutorClient {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Consider passing player name in arguments to start the communication.");
            System.exit(0);
        }
        Player player = new Player(args[0], checkForInitiator(args[0]));
        Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        beginCommunication(player, bufferedReader, printWriter);
    }

    /**
     * BeginCommunication method is responsible for initializing the communication between the Player class objects
     * which are available on different processes
     *
     * @param player
     * @param bufferedReader
     * @param printWriter
     * @throws IOException
     */
    private static void beginCommunication(Player player, BufferedReader bufferedReader, PrintWriter printWriter) throws IOException {
        int messageCount = 0;
        while (messageCount < MAX_MESSAGE_COUNT) {
            if (player.isInitiator()) {
                String message = String.format("%s: message #[%s]", player.getName(), messageCount + 1);
                System.out.println(message);
                printWriter.println(message);

                String reply = bufferedReader.readLine();
                System.out.println(reply);
            } else {
                String message = bufferedReader.readLine();
                System.out.println(message);
                String reply = String.format("%s: reply to %s", player.getName(), message);
                System.out.println(reply);
                printWriter.println(reply);
            }
            messageCount++;
        }

    }

    /**
     * This method checks whether the name of the player entered via args is initiator and returns the matching result.
     *
     * @param name
     * @return
     */
    private static boolean checkForInitiator(String name) {
        return INITIATOR.equals(name);
    }
}
