package com.airline.flight.exeptions;

import java.util.Date;

public class ErrorMessage {

    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

    public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
    public void setTimestamp(Date date){
        this.timestamp = date;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
            "statusCode=" + statusCode +
            ", timestamp=" + timestamp +
            ", message='" + message + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
