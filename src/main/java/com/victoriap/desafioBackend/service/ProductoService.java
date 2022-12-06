package com.victoriap.desafioBackend.service;

import com.victoriap.desafioBackend.exception.ResourceNotFoundException;
import com.victoriap.desafioBackend.model.CrearProducto;
import com.victoriap.desafioBackend.model.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductoService {

    Producto altaProducto(CrearProducto Producto);

    void bajaProducto(Integer id);

    ResponseEntity findById(Integer id);

    List<Producto> listaProductos();

}
