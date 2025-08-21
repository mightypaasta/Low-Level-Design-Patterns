package patterns.logging_framework;

import java.time.Instant;

class Message {
    private final Instant timestamp;
    private final ILogLevel logLevel;
    private final String content;

    public Message(ILogLevel logLevel, String content){
        this.timestamp = Instant.now();
        this.logLevel = logLevel;
        this.content = content;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getLogLevel() {
        return logLevel.getLevel();
    }

    public String getContent(){
        return content;
    }
}
