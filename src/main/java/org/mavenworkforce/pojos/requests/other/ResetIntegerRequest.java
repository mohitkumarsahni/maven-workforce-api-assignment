package org.mavenworkforce.pojos.requests.other;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ResetIntegerRequest {

    @NotNull(message = "The value can not be null.")
    @Min(value = 0L, message = "The value must be positive.")
    private Long current;
}
