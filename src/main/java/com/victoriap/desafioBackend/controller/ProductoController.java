package com.victoriap.desafioBackend.controller;

import com.victoriap.desafioBackend.model.Producto;
import com.victoriap.desafioBackend.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
@Tag(name = "Api productos", description = "APIs Rest de Producto ")
public class ProductoController {
    
    @Autowired
    ProductoService service;

    @PostMapping("/Productos")
    @Operation(description = "Alta de Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "201", description = "Respuesta exitosa"),
            @ApiResponse(responseCode  = "500", description = "Error interno del servidor")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity alta (@RequestBody Producto producto){
        service.altaProducto(producto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/Productos/{idProducto}")
    @Operation(description = "Baja de Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Ok"),
            @ApiResponse(responseCode  = "404", description = "El producto no existe"),
            @ApiResponse(responseCode  = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<?> baja(@PathVariable Integer idProducto){
        service.bajaProducto(idProducto);
        return ResponseEntity.ok("El producto con ID " + idProducto + " ha sido eliminado exitosamente");
    }

    @GetMapping("/Productos/{idProducto}")
    @Operation(description = "encuentra Producto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Ok", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Producto.class)) }),
            @ApiResponse(responseCode  = "404", description = "El producto no existe",
                    content = @Content),
            @ApiResponse(responseCode  = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    public ResponseEntity<?> findById(@PathVariable int idProducto){
        return ResponseEntity.ok(service.findById(idProducto));
    }

    @GetMapping("/Productos")
    @Operation(description = "Retorna la lista de productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Ok"),
            @ApiResponse(responseCode  = "500", description = "Error del servidor")
    })
    public ResponseEntity<?> listaProductos(){
        List<Producto> res = service.listaProductos();
        return ResponseEntity.ok(res);
    }
}
