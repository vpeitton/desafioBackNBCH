package com.victoriap.desafioBackend.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "producto")
public class Producto implements Serializable {

    @Serial
    private static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idProducto;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank
    @Size(min = 0, max = 30)
    String nombre;

    @Column(name = "descripcion", nullable = false, length = 5000)
    @NotBlank
    @Size(min = 0, max = 50)
    String descripcion;

    @Column(name = "precio", nullable = false)
    Double precio;

    @Column(name = "fechaCreacion", nullable = false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    public Producto() {
    }
}
