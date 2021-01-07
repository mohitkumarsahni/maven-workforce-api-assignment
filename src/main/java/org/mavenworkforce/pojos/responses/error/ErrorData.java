package org.mavenworkforce.pojos.responses.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorData {
    private String errorMessage;
    private String errorCode;
}
