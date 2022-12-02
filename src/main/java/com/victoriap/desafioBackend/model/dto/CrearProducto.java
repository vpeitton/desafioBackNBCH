package com.victoriap.desafioBackend.model.dto;

import lombok.Data;

@Data
public class CrearProducto {

    Integer idProducto;
    String nombre;

    public CrearProducto() {
    }
}
