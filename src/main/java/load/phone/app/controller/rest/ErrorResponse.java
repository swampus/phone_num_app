package load.phone.app.controller.rest;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("error")
public class ErrorResponse implements Response {

    @JsonProperty("exception")
    private String exception;

    @JsonProperty("exception")
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(String exception, String message) {
        this.exception = exception;
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
