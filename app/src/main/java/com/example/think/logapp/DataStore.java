package com.example.think.logapp;

/**
 * Created by Think on 2017/12/6.
 */

public class DataStore {
    private String logName;
    private String logContext;

    public String getLogName() {
        return logName;
    }
    public void setLogName(String logName) {
        this.logName = logName;
    }
    public String getLogContext() {
        return logContext;
    }
    public void setLogContext(String logContext) {
        this.logContext = logContext;
    }

    @Override
    public String toString() {
        return logName;
    }
    public DataStore(String logName,String logContext) {
        super();
        this.logName = logName;
        this.logContext=logContext;
    }
}
