package com.victoriap.desafioBackend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victoriap.desafioBackend.exception.ErrorNoEncontrado;
import com.victoriap.desafioBackend.exception.FailedPreconditionRestException;
import com.victoriap.desafioBackend.exception.InternalServerException;
import com.victoriap.desafioBackend.model.entity.Producto;
import com.victoriap.desafioBackend.repository.ProductoRepository;
import com.victoriap.desafioBackend.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository repository;

    @Autowired
    ObjectMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoServiceImpl.class);

    @Override
    public void altaProducto(Producto producto) {
        if (repository.existsById(producto.getIdProducto()))
            throw new FailedPreconditionRestException("Ya existe un producto con ese id");
        try {
            repository.save(producto);
        } catch (Exception e) {
            LOGGER.error("ocurrio un error inesperado");
            LOGGER.error(e.getMessage());
            throw new InternalServerException("Falla al hacer la consulta a la BD");
        }
    }

    @Override
    public void bajaProducto(Integer idProducto) {
        if (!repository.existsById(idProducto)) {
            throw new FailedPreconditionRestException("No hay Mayorista con ese nombre");
        }
        try {
            repository.deleteByIdProducto(idProducto);
        } catch (Exception e) {
            throw new InternalServerException("Falla al hacer la consulta a la BD");
        }
    }

    @Override
    public ResponseEntity findById(Integer idProducto) {
        try {
            Optional<Producto> producto = Optional.ofNullable(repository.findById(idProducto).orElse(null));

            return new ResponseEntity(producto, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("ocurrio un error inesperado");
            LOGGER.error(e.getMessage());
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @Override
    public List<Producto> listaProductos() {
        List<Producto> lista = new ArrayList<>();
        try{
            lista= repository.findAll((Sort.by("idIdioma").and(Sort.by("etiqueta").and(Sort.by("pantalla")))));
        }catch (Exception e){
            throw new InternalServerException("Falla al hacer la consulta a la BD");
        }
        if(lista!=null && lista.isEmpty())
            throw new ErrorNoEncontrado("No hay registros");
        return lista;
    }
}
