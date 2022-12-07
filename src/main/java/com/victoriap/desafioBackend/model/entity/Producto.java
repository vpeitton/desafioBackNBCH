package com.victoriap.desafioBackend.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Data
@Schema
@AllArgsConstructor
@Table(name = "producto")
public class Producto {


    @Id
    @NotBlank
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank
    @Size(min = 0, max = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 5000)
    @NotBlank
    @Size(min = 0, max = 5000)
    private String descripcion;

    @NotBlank
    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "fechaCreacion", nullable = false)
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
}
