package com.example.m6l2.modelo;

import java.util.HashMap;
import java.util.Map;

public class Carrito {

    private Map<Producto, Integer> items;

    public Carrito() {
        this.items = new HashMap<>();
    }

    public void addItem(Producto producto) {
        items.put(producto, items.getOrDefault(producto, 0) + 1);
    }

    public void removeItem(Producto producto) {
        items.remove(producto);
    }

    public void updateQuantity(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            removeItem(producto);
        } else {
            items.put(producto, cantidad);
        }
    }

    public Map<Producto, Integer> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrecio() * entry.getValue();
        }
        return total;
    }
}