package com.victoriap.desafioBackend.service;

import com.victoriap.desafioBackend.model.entity.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductoService {

    //Alta usuario
    public void altaProducto(Producto Producto);
    //DELETE Producto
    public void bajaProducto(Integer id);
    //FIND BY Id
    public ResponseEntity findById (Integer id);
    //Lista de productos
    public List<Producto> listaProductos();

}
