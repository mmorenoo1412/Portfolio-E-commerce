package com.example.m6l2.servicio;

import com.example.m6l2.modelo.*;
import com.example.m6l2.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class CarritoService {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final OrdenRepository ordenRepository;

    // 🧠 In-memory carts per user
    private final Map<String, Carrito> carritos = new HashMap<>();

    public CarritoService(UsuarioRepository usuarioRepository,
                          ProductoRepository productoRepository,
                          OrdenRepository ordenRepository) {
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.ordenRepository = ordenRepository;
    }

    private String getUsernameActual() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private Usuario getUsuarioActual() {
        return usuarioRepository.findByUsername(getUsernameActual())
                .orElseThrow();
    }

    public Carrito obtenerCarrito() {
        String username = getUsernameActual();
        return carritos.computeIfAbsent(username, k -> new Carrito());
    }

    @Transactional
    public void agregarProducto(Integer productoId) {
        Carrito carrito = obtenerCarrito();
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow();
        carrito.addItem(producto);
    }

    @Transactional
    public void eliminarProducto(Integer productoId) {
        Carrito carrito = obtenerCarrito();
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow();
        carrito.removeItem(producto);
    }

    @Transactional
    public void checkout() {
        Carrito carrito = obtenerCarrito();
        Usuario usuario = getUsuarioActual();

        Orden orden = new Orden(usuario, carrito.getTotalPrice());

        carrito.getItems().forEach((producto, cantidad) -> {
            orden.addItem(new OrdenItem(
                    orden,
                    producto,
                    cantidad
            ));
        });

        ordenRepository.save(orden);

        carrito.getItems().clear(); // clear cart
    }
}