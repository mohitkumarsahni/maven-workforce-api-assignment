package org.mavenworkforce.pojos.responses.success;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class SuccessResponse {
    private String response = "SUCCESS";
    private Object data;
    private Instant timestamp;

    public SuccessResponse(Object data, Instant timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }
}