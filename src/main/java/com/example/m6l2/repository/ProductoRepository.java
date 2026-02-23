package com.example.m6l2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.m6l2.modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	
	
}
