package com.victoriap.desafioBackend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema
public class ErrorNoEncontrado {

    @NotBlank
    private String codigo;

    private String mensaje;
}
