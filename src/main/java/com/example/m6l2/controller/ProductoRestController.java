package com.example.m6l2.controller;

import com.example.m6l2.modelo.Producto;
import com.example.m6l2.servicio.ProductoServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    private final ProductoServiceJPA productoServiceJPA;

    @Autowired
    public ProductoRestController(ProductoServiceJPA productoServiceJPA) {
        this.productoServiceJPA = productoServiceJPA;
    }

    @GetMapping
    public List<Producto> listar() {
        return productoServiceJPA.listarConJPA();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto agregar(@RequestBody Producto producto) {
        return productoServiceJPA.agregarconJPA(producto);
    }
}