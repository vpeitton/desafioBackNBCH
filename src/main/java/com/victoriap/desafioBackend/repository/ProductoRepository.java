package com.victoriap.desafioBackend.repository;

import com.victoriap.desafioBackend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository <Producto,Integer>{

    @Modifying
    @Transactional
    void deleteByIdProducto(Integer id);

    Optional<Producto> findById(Integer id);

    List<Producto> findAll();

}
