package com.victoriap.desafioBackend.service;

import com.victoriap.desafioBackend.exception.ResourceNotFoundException;
import com.victoriap.desafioBackend.model.CrearProducto;
import com.victoriap.desafioBackend.model.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Producto altaProducto(CrearProducto Producto);

    void bajaProducto(Integer id);

    Optional<Producto> findById(Integer id);

    List<Producto> listaProductos();

}
