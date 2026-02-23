package com.example.m6l2.controller;

import com.example.m6l2.modelo.Producto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class ClienteRestController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/productos/api")
    public String listarDesdeApi(Model model) {
        Producto[] productos = restTemplate.getForObject("http://localhost:8080/api/productos", Producto[].class);
        model.addAttribute("productos", productos);
        return "productos_api"; 
    }
}