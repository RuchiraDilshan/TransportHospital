package com.example.TransportHospital.dto;

import java.time.LocalDateTime;

public class SuccessResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private Object data;

    public SuccessResponse() {
        this.timestamp = LocalDateTime.now();
        this.status = 200;
    }

    public SuccessResponse(String message, Object data) {
        this();
        this.message = message;
        this.data = data;

    }

    public SuccessResponse(String message) {
        this();
        this.message = message;

    }

    // Getters and setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
