package com.chat.app.messaging.impl;

import com.chat.app.Player;
import com.chat.app.messaging.MessageService;

import static com.chat.app.utils.Constants.MAX_MESSAGE_COUNT;
import static com.chat.app.utils.Constants.MESSAGE_FORMAT;
import static com.chat.app.utils.Constants.REPLY_FORMAT;

/**
 * This Service class is responsible for handling the communication between two Player class objects communicating in the same process
 */
public class MessageServiceImpl implements MessageService {
    private boolean isInitiatorTurn = true;

    /**
     * A synchronized method which is responsible for smooth functioning of sending and receiving messages related to both Player instances.
     * @param player
     * @throws InterruptedException
     */
    @Override
    public synchronized void initiateCommunication(Player player) throws InterruptedException {
        while (player.getMessageCount() < MAX_MESSAGE_COUNT) {
            if (player.isInitiator()) {
                while (!isInitiatorTurn) {
                    wait();
                }
                Player.lastMessage = sendMessage(player);
                isInitiatorTurn = false;
            } else {
                while (isInitiatorTurn) {
                    wait();
                }
                reply(player, Player.lastMessage);
                isInitiatorTurn = true;
            }
            player.setMessageCount(player.getMessageCount() + 1);
            notify();
        }
    }

    /**
     * This method is responsible for displaying messages that are being sent on console in a desired format.
     * @param player
     * @return
     */
    private String sendMessage(Player player) {
        String message = String.format("message #[%s]", player.getMessageCount() + 1);
        System.out.printf((MESSAGE_FORMAT) + "%n", player.getName(), message,
                player.getOpponent().getName());
        return message;
    }

    /**
     * This method is responsible for sending replies from the responder object with the received message concatenated in the reply
     * @param player
     * @param message
     */
    private void reply(Player player, String message) {
        System.out.printf((REPLY_FORMAT) + "%n", player.getName(), message, player.getOpponent().getName());
    }
}
