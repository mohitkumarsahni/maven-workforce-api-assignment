package org.mavenworkforce.pojos.responses.error;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ErrorResponse {
    private String response = "ERROR";
    private ErrorData error;
    private Instant timestamp;

    public ErrorResponse(ErrorData error, Instant instant) {
        this.error = error;
        this.timestamp = instant;
    }
}
