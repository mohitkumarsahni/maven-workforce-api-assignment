package org.mavenworkforce.pojos.requests.other;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ResetIntegerRequest {

    @Min(value = 0L, message = "The value must be positive.")
    private Long current;
}
