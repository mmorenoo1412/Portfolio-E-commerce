package com.example.m6l2.servicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.m6l2.modelo.Producto;


@Service
public class ProductoServiceImpl implements ProductoService{

	@Override
	public List<Producto> listar() {
		// TODO Auto-generated method stub
		return Arrays.asList(
				new Producto(1, "Mouse", 5000),
				new Producto(2, "Laptop", 400000),
				new Producto(3, "Teclado", 10000)
				);
	}

	
	
	
}
