package com.example.m6l2.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.m6l2.modelo.Producto;
import com.example.m6l2.repository.ProductoRepository;

@Service
public class ProductoServiceJPA {

	@Autowired
	private final ProductoRepository repository;
	
	public ProductoServiceJPA(ProductoRepository repository) {
		this.repository=repository;	
	}
	
	public List<Producto> listarConJPA() {
		return repository.findAll();
	}
	
	@Transactional
	public Producto agregarconJPA(Producto producto) {
		return repository.save(producto);
		
	}

	public void agregarConJPA(Producto producto) {
		// TODO Auto-generated method stub
		
	}
	
	
}
