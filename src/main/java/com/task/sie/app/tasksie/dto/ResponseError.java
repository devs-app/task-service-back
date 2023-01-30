package com.task.sie.app.tasksie.dto;

public class ResponseError extends Exception {
    public ResponseError(String message){
        super(message);
    }
}
