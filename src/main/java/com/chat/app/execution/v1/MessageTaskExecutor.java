package com.chat.app.execution.v1;

import com.chat.app.Player;
import com.chat.app.messaging.MessageService;
import com.chat.app.messaging.impl.MessageServiceImpl;

import static com.chat.app.utils.Constants.INITIATOR;
import static com.chat.app.utils.Constants.RESPONDER;

/**
 * This is the main class that is being used for initializing communication between the two instances of Player class in
 * same process
 * Package V1 has the execution class related to communication in same java process.
 */
public class MessageTaskExecutor {
    public static void main(String[] args) {
        Player initiator = new Player(INITIATOR, true);
        Player responder = new Player(RESPONDER, false);
        initiator.setOpponent(responder);
        responder.setOpponent(initiator);
        beginExecution(initiator, responder);
    }

    /**
     * BeginExecution method starts two threads for the two instances of Player class respectively and internally
     * calls messagingService's initiateCommunication method to initiate the communication between two objects.
     * @param initiator
     * @param responder
     */
    public static void beginExecution(Player initiator, Player responder) {
        MessageService messageService = new MessageServiceImpl();
        Thread initiatorThread = new Thread(() -> {
            try {
                messageService.initiateCommunication(initiator);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread responderThread = new Thread(() -> {
            try {
                messageService.initiateCommunication(responder);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        initiatorThread.start();
        responderThread.start();
    }
}