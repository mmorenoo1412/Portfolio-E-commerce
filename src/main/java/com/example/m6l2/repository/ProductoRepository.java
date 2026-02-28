package com.example.m6l2.repository;

import com.example.m6l2.modelo.Producto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

	Optional<Producto> findById(Integer id);
    // JpaRepository provides findById(), findAll(), save(), etc.
}