package patterns.logging_framework;

interface ILogLevel {
    public String getLevel();
}

class Debug implements ILogLevel{
    public String getLevel(){
        return "DEBUG";
    }
}

class Info implements  ILogLevel{
    @Override
    public String getLevel() {
        return "INFO";
    }
}

class Warning implements ILogLevel{

    @Override
    public String getLevel() {
        return "WARNING";
    }
}

class Error implements ILogLevel{

    @Override
    public String getLevel() {
        return "ERROR";
    }
}

class Fatal implements ILogLevel{

    @Override
    public String getLevel() {
        return "FATAL";
    }
}