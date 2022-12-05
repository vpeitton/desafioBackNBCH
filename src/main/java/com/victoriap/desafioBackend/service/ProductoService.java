package com.victoriap.desafioBackend.service;

import com.victoriap.desafioBackend.model.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductoService {

    //Alta de producto
    void altaProducto(Producto Producto);

    //DELETE Producto
    void bajaProducto(Integer id);

    //FIND BY Id
    ResponseEntity findById(Integer id);

    //Lista de productos
    List<Producto> listaProductos();

}
