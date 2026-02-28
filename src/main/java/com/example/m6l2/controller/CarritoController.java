package com.example.m6l2.controller;

import com.example.m6l2.modelo.Carrito;
import com.example.m6l2.modelo.Producto;
import com.example.m6l2.servicio.ProductoServiceJPA;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    private final ProductoServiceJPA productoServiceJPA;

    public CarritoController(ProductoServiceJPA productoServiceJPA) {
        this.productoServiceJPA = productoServiceJPA;
    }

    // Ver carrito
    @GetMapping
    public String verCarrito(HttpSession session, Model model) {
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        }

        model.addAttribute("carrito", carrito);
        return "carrito";
    }

    // Agregar producto al carrito
    @PostMapping("/agregar")
    public String agregarAlCarrito(@RequestParam Long productoId,
                                   HttpSession session) {

        Carrito carrito = (Carrito) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        }

        Producto producto = productoServiceJPA
                .listarConJPA()
                .stream()
                .filter(p -> p.getId().equals(productoId))
                .findFirst()
                .orElse(null);

        if (producto != null) {
            carrito.addItem(producto);
        }

        // 🔥 FIX HERE — redirect to "/"
        return "redirect:/";
    }
}