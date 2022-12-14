package com.victoriap.desafioBackend.controller;

import com.victoriap.desafioBackend.exception.InternalServerException;
import com.victoriap.desafioBackend.exception.ResourceNotFoundException;
import com.victoriap.desafioBackend.model.dto.CrearProducto;
import com.victoriap.desafioBackend.model.error.ErrorGenerico;
import com.victoriap.desafioBackend.model.error.ErrorNoEncontrado;
import com.victoriap.desafioBackend.model.entity.Producto;
import com.victoriap.desafioBackend.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping
@Tag(name = "Api productos", description = "APIs Rest para gestionar productos")
public class ProductoController {

    ProductoService service;

    @Autowired
    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @PostMapping("/Productos")
    @Operation(description = "Alta de Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Respuesta exitosa", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Producto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorGenerico.class))})
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Producto> altaProducto(@RequestBody(description = "Producto a a??adir.", required = true,
            content = @Content(
                    schema = @Schema(implementation = CrearProducto.class)))
                                  @Valid @org.springframework.web.bind.annotation.RequestBody CrearProducto productoDTO) throws InternalServerException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.altaProducto(productoDTO));

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
    public ResponseEntity<?> bajaProducto(@Parameter(description = "Id del producto a ser eliminado") @PathVariable Integer idProducto) {
        service.bajaProducto(idProducto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Producto con ID " + idProducto + " eliminado");
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
    public ResponseEntity<Optional<Producto>> encuentraPorId(@Parameter(description = "Id del producto a ser buscado") @PathVariable int idProducto) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(idProducto));
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
    public ResponseEntity<List<Producto>> listaProductos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listaProductos());
    }
}
