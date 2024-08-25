package com.chat.app.utils;

/**
 * All the utility constants which are being used in the source code are defined in this class.
 */
public final class Constants {

    public static final int MAX_MESSAGE_COUNT = 10;
    public static final String MESSAGE_FORMAT = "%s sends: [%s] to %s";
    public static final String REPLY_FORMAT = "%s replies to message: [%s] sent by [%s]";
    public static final String SERVER_HOST = "localhost";
    public static final int SERVER_PORT = 9999;
    public static final String INITIATOR = "initiator";
    public static final String RESPONDER = "responder";
    private Constants() {
        throw new UnsupportedOperationException("Cannot initialize utility classes.");
    }
}
