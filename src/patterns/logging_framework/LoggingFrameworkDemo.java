package patterns.logging_framework;

public class LoggingFrameworkDemo {

    public static void runDemo(){

        Logger instance = Logger.getInstance();

        ILogLevel debug = new Debug();
        ILogLevel info = new Info();
        ILogLevel warning = new Warning();
        ILogLevel error = new Error();
        ILogLevel fata = new Fatal();

        LogStorage console = Console.getInstance();

        Message message = new Message(debug,"Debugging java code for logging framework.");

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                instance.logMessage(message, console);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                Message msg2 = new Message(info, "Info: Practice design patterns for building good software.");
                instance.logMessage(msg2, console);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();
    }

}
