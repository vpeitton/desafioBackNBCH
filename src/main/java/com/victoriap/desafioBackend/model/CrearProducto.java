package com.victoriap.desafioBackend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema
public class CrearProducto {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Size(max = 5000)
    private String descripcion;

    @NotBlank
    private double precio;
}
