package com.chat.app;

/**
 * This is the main class whose instances are used to build a communication between them.
 */
public class Player {

    private final String name;
    private Player opponent;
    private int messageCount;
    private final boolean initiator;
    public static String lastMessage;

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public Player(String name, boolean initiator) {
        this.name = name;
        this.initiator = initiator;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public String getName() {
        return name;
    }

    public boolean isInitiator() {
        return initiator;
    }
}
