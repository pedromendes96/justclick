package org.justclick.project.dto;

import java.util.Date;

import org.justclick.project.document.LogRegistry;

public class LogRegistryDto {
    private String id;
    private Date timestamp;
    private String log;

    public LogRegistryDto(LogRegistry logRegistry) {
        this.id = logRegistry.getId();
        this.timestamp = logRegistry.getTimestamp();
        this.log = logRegistry.getLog();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    
}
