package com.victoriap.desafioBackend.service;

import com.victoriap.desafioBackend.model.dto.CrearProducto;
import com.victoriap.desafioBackend.model.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Producto altaProducto(CrearProducto Producto);

    void bajaProducto(Integer id);

    Optional<Producto> findById(Integer id);

    List<Producto> listaProductos();

}
