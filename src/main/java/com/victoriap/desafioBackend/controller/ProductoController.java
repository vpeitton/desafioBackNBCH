package com.victoriap.desafioBackend.controller;

import com.victoriap.desafioBackend.exception.InternalServerException;
import com.victoriap.desafioBackend.exception.ResourceNotFoundException;
import com.victoriap.desafioBackend.model.CrearProducto;
import com.victoriap.desafioBackend.model.error.ErrorGenerico;
import com.victoriap.desafioBackend.model.error.ErrorNoEncontrado;
import com.victoriap.desafioBackend.model.Producto;
import com.victoriap.desafioBackend.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping
@Tag(name = "Api productos", description = "APIs Rest para gestionar productos")
public class ProductoController {

    @Autowired
    ProductoService service;

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @PostMapping("/Productos")
    @Operation(description = "Alta de Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Respuesta exitosa", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Producto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorGenerico.class))})
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> alta(@RequestBody(description = "Producto a añadir.", required = true,
            content = @Content(
                    schema = @Schema(implementation = CrearProducto.class)))
                                  @Valid @org.springframework.web.bind.annotation.RequestBody CrearProducto productoDTO) throws InternalServerException {
        Producto producto = null;
        try {
            producto = service.altaProducto(productoDTO);
            logger.info("El producto " + producto.getNombre() + " con ID " + producto.getIdProducto() + " ha sido creado");
            return ResponseEntity.status(HttpStatus.CREATED).body(producto);
        } catch (InternalServerException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/Productos/{idProducto}")
    @Operation(description = "Baja de Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Respuesta exitosa"),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorNoEncontrado.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorGenerico.class))})
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> baja(@Parameter(description = "Id del producto a ser eliminado") @PathVariable Integer idProducto) {
        try {
            service.bajaProducto(idProducto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/Productos/{idProducto}")
    @Operation(description = "encuentra Producto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Producto.class))}),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorNoEncontrado.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorGenerico.class))})
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> encuentraPorId(@Parameter(description = "Id del producto a ser buscado") @PathVariable int idProducto) throws ResourceNotFoundException {
        ResponseEntity producto = null;
        try {
            producto = service.findById(idProducto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("/Productos")
    @Operation(description = "Retorna la lista de productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Producto.class))}),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorGenerico.class))})
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> listaProductos() {
        List<Producto> res = service.listaProductos();
        if (res.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
