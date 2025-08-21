package patterns.logging_framework;

interface ILogStorage {
    void store(Message msg);
}

abstract class LogStorage{

    public synchronized void syncStore(Message msg){
        System.out.println("Thread: "+Thread.currentThread()+" storing");
        store(msg);
        System.out.println("Thread: "+Thread.currentThread()+" stored.");
    }

    protected abstract void store(Message msg);
}

class Console extends LogStorage{

    private static Console instance;

    public static Console getInstance() {
        if(instance == null){
            instance = new Console();
        }
        return instance;
    }

    protected synchronized void store(Message msg) {
        System.out.println("Log Level: "+ msg.getLogLevel());
        System.out.println("timestamp: "+ msg.getTimestamp());
        System.out.println("Content: "+ msg.getContent());
    }
}

class File implements ILogStorage{

    @Override
    public void store(Message msg) {

    }
}

class Database implements ILogStorage{

    @Override
    public void store(Message msg) {

    }
}
