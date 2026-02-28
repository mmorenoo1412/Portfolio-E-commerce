package com.example.m6l2.controller;

import com.example.m6l2.dao.ProductoDAOJdbc;
import com.example.m6l2.repository.ProductoRepository;
import com.example.m6l2.servicio.ProductoServiceImpl;
import com.example.m6l2.servicio.ProductoServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProductoController {

    @Autowired
    private ProductoServiceImpl productoservice;

    @Autowired
    private ProductoDAOJdbc productoDAOJdbc;

    @Autowired
    private ProductoServiceJPA productoServiceJPA;

    // Main page — populate products so tables always show
    @GetMapping
    public String listar(Model model) {
        // You can choose either JPA or in-memory list here
        model.addAttribute("productos", productoServiceJPA.listarConJPA());
        return "productos"; // main productos.html page
    }

   // @GetMapping("productos")
   // public String listarJdbc(Model model) {
   //     model.addAttribute("productos", productoDAOJdbc.listar());
   //     return "productos";
   // }

   // @GetMapping("productos")
   // public String listarJPA(Model model) {
     //  model.addAttribute("productos", productoServiceJPA.listarConJPA());
      // return "productos";
    //}
    
}
