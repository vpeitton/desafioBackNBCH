package com.victoriap.desafioBackend.model.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema
@Builder
public class ErrorGenerico {

    @NotBlank
    private String codigo;

    private String mensaje;
}
