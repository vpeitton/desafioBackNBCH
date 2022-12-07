package com.victoriap.desafioBackend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victoriap.desafioBackend.exception.InternalServerException;
import com.victoriap.desafioBackend.exception.ResourceNotFoundException;
import com.victoriap.desafioBackend.model.CrearProducto;
import com.victoriap.desafioBackend.model.Producto;
import com.victoriap.desafioBackend.repository.ProductoRepository;
import com.victoriap.desafioBackend.service.ProductoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ProductoServiceImpl implements ProductoService {

    ProductoRepository repository;
    ObjectMapper mapper;

    @Autowired
    public ProductoServiceImpl(ProductoRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Producto altaProducto(CrearProducto crearProducto) {
        Producto producto = mapper.convertValue(crearProducto, Producto.class);
        if (crearProducto.getNombre() == null || crearProducto.getNombre().equals("")) {
            log.error("El campo de nombre está vacío o es nulo");
            throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", "El campo de nombre está vacío o es nulo");
        }
        if (crearProducto.getPrecio() == 0) {
            log.error("El campo de precio está vacío");
            throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", "El campo de nombre está vacío o es nulo");
        }
        try {
            repository.save(producto);
        } catch (InternalServerException e) {
            throw new InternalServerException(e.getStatusCode(), e.getMessage(), e.getStatusText());
        }
        
        return producto;
    }

    @Override
    public void bajaProducto(Integer idProducto) throws ResourceNotFoundException {
        if (!repository.existsById(idProducto)) {
            throw new ResourceNotFoundException("No hay producto con el id " + idProducto);
        }
        try {
            repository.deleteByIdProducto(idProducto);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getStatusCode(), e.getMessage(), e.getStatusText());
        } catch (InternalServerException e) {
            throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", "Error interno del servidor");
        }
    }

    @Override
    public Optional<Producto>  findById(Integer idProducto) throws ResourceNotFoundException {
        Optional<Producto> producto = null;
        try {
            producto = Optional.ofNullable(repository.findById(idProducto).orElseThrow(ResourceNotFoundException::new));
        } catch (ResourceNotFoundException e) {
            log.warn("No hay registros");
            log.error("No hay registros para el producto con id " + idProducto);
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, e.getMessage(), e.getStatusText());
        }  catch (InternalServerException e) {
            throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", "Error interno del servidor");
        }
        return producto;
    }

    @Override
    public List<Producto> listaProductos() {
        List<Producto> lista = new ArrayList<>();
        try {
            lista = repository.findAll();
        } catch (InternalServerException e) {
            throw new InternalServerException(e.getStatusCode(), e.getMessage(), e.getStatusText());
        }
        if (lista != null && lista.isEmpty())
            throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", "La lista de productos esta vacía");
        return lista;
    }
}
