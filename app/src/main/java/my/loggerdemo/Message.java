package my.loggerdemo;

import android.support.annotation.VisibleForTesting;

/**
 * Message class keeps a single log message details. Immutable.
 *
 * Created by Ilian Georgiev.
 */
/* package */ class Message {

    public static final String DELIMITER = ";";

    private static final int DEFAULT_CALLING_METHOD_INDEX = 7;

    private long currentTime;
    private final Identifier identifier;
    private byte[] byteArr;

    public Message(String message) {
        this(message, DEFAULT_CALLING_METHOD_INDEX);
    }

    public Message(String message, int callingMethodIndex) {
        this.currentTime = System.currentTimeMillis();
        this.identifier = new Identifier(callingMethodIndex);

        if (message != null) {
            message = message.replace(DELIMITER, " ").replace(Identifier.DELIMITER, " ");
            byteArr = message.getBytes();
        }
    }

    @VisibleForTesting
    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{ ").append("currentTime: ").append(currentTime).append(";");
        sb.append(" identifier: ").append(identifier.toString()).append(";");
        if (byteArr != null) {
            sb.append(" message: ").append(new String(byteArr)).append(";");
        }
        sb.append("}");

        return sb.toString();
    }

    private static class Identifier {

        public static final String DELIMITER = "|";

        private final byte[] value;

        /**
         * Class constructor.
         * @param callingMethodIndex Identifies the calling method in the stack trace. This number
         *                           depends on how nested the call to this constructor happened.
         *                           As all logging is intended to be used from static method there
         *                           is a default value to be used
         *                           {@link #DEFAULT_CALLING_METHOD_INDEX}.
         */
        public Identifier(int callingMethodIndex) {
            Thread t = Thread.currentThread();
            StackTraceElement[] stack = t.getStackTrace();

            int k = callingMethodIndex;
            String str = stack[k].getClassName() + DELIMITER + stack[k].getMethodName()
                    + DELIMITER + stack[k].getFileName() + DELIMITER + stack[k].getLineNumber()
                    + DELIMITER + t.getId();

            value = str.getBytes();
        }

        @Override
        public String toString() {
            return new String(value);
        }
    }
}
