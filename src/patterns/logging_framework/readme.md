### Requirements
1. Log Levels = DEBUG, INFO, WARNING, ERROR and FATAL
2. Message = Timestamp, log level, Content
3. Multiple Output Destinations = console, file and DB
4. Configurable = Log level, Output Destination
5. Thread-safe for handling concurrent logging from multiple thread.
6. Extensible = New log levels, Output destinations



Class:

<<ILogLevel>>
- public String getLevel()

<<Debug>> implements ILogLevel
<<Info>> implements ILogLevel
<<Warning>> implements ILogLevel
<<Error>> implements ILogLevel
<<Error>> implements ILogLevel

<<Message>>
- Timestamp : Instant
- log level : String
- Content: String

<<ILogging>>
- public void log(Message msg)

<<Console>> implements ILogging
<<File>> implements ILogging
<<Database>> implements ILogging

<<Logger>>
- getInstance(): Logger (Singleton)
- setMessage(Message msg): void
- setOutputDestination(): void
- logMessage(): boolean (synchronized)

