package com.utilities;
public class JsonResponse {
    
    private int responseCode;
    private String message;
    private String description;

    public JsonResponse(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public JsonResponse() {
    }

    public int getresponseCode() {
        return responseCode;
    }

    public void setresponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
