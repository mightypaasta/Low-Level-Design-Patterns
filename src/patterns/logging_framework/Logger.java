package patterns.logging_framework;

class Logger {
    private static Logger instance;

    private Logger(){
    }

    public static synchronized Logger getInstance(){
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }

    void logMessage(Message msg, LogStorage storage){
        storage.store(msg);
    }
}
