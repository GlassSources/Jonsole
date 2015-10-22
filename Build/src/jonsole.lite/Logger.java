package jonsole.lite;

import jonsole.lite.referencecode.Jonsole;

public class Logger {
    public enum Log{
        INFO,
        SEVERE,
        WARNING,
        FAILURE,
    }
    private String loggerName;
    public Logger(String loggerName){ this.loggerName = loggerName; }
    public void log(Log logType, String message){
        if(logType == null || message == null){
            return;
        }
        switch (logType){
            case INFO:
                Jonsole.consoleArea.appendText("[" + loggerName + "][INFO] " + message + "\n");
                break;
            case SEVERE:
                Jonsole.consoleArea.appendText("[" + loggerName + "][SEVERE] " + message + "\n");
                break;
            case WARNING:
                Jonsole.consoleArea.appendText("[" + loggerName + "][WARNING] " + message + "\n");
                break;
            case FAILURE:
                Jonsole.consoleArea.appendText("[" + loggerName + "][FAILURE] " + message + "\n");
                break;
            default:
                Jonsole.consoleArea.appendText(message + "\n");
                break;
        }
    }
    public void clearConsoleArea(){
        Jonsole.consoleArea.clear();
    }
    public void info(String message){
        if(message == null){
            return;
        }
        Jonsole.consoleArea.appendText("[" + loggerName + "][INFO] " + message + "\n");
    }
    public void severe(String message){
        if(message == null){
            return;
        }
        Jonsole.consoleArea.appendText("[" + loggerName + "][SEVERE] " + message + "\n");
    }
    public void warn(String message){
        if(message == null){
            return;
        }
        Jonsole.consoleArea.appendText("[" + loggerName + "][WARNING] " + message + "\n");
    }
    public void failure(String message){
        if(message == null){
            return;
        }
        Jonsole.consoleArea.appendText("[" + loggerName + "][FAILURE] " + message + "\n");
    }
}
