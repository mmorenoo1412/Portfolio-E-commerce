package com.example.m6l2.controller;

import com.example.m6l2.modelo.Producto;
import com.example.m6l2.servicio.ProductoServiceJPA;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductoServiceJPA productoServiceJPA;

    public AdminController(ProductoServiceJPA productoServiceJPA) {
        this.productoServiceJPA = productoServiceJPA;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/panel")
    public String panelAdmin(Model model) {
        model.addAttribute("productos", productoServiceJPA.listarConJPA());
        model.addAttribute("nuevoProducto", new Producto());
        return "admin"; // admin.html
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/panel/agregar")
    public String agregarProducto(@ModelAttribute Producto nuevoProducto) {
        productoServiceJPA.agregarconJPA(nuevoProducto);
        return "redirect:/admin/panel";
    }
}