package my.loggerdemo;

/**
 * Created by Ilian Georgiev.
 */
/* package */ class Message {

    public static final String DELIMITER = ";";

    private static final int CALLING_METHOD_INDEX = 5;

    private long currentTime;
    private final Identifier identifier;
    private byte[] byteArr;

    public Message(String message) {
        this.currentTime = System.currentTimeMillis();
        this.identifier = new Identifier();

        if (message != null) {
            message = message.replace(DELIMITER, " ").replace(Identifier.DELIMITER, " ");
            byteArr = message.getBytes();
        }
    }

    /**
     * @return Returns export variant of this message.
     */
    public String export() {
        String message = (byteArr != null) ? new String(byteArr) : "";
        return currentTime + DELIMITER + identifier + DELIMITER + message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{ ").append(" currentTime: ").append(currentTime).append(";");
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

        public Identifier() {
            Thread t = Thread.currentThread();
            StackTraceElement[] stack = t.getStackTrace();

            int k = CALLING_METHOD_INDEX;
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
