package com.fmovies.restapimongodb;

public class CustomResponse {

    private Object data;
    private String message;

    public CustomResponse() {
    }

    public CustomResponse(Object data, String message) {
        this.data = data;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
