package com.example.m6l2.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordenes")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    private double total;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<OrdenItem> items = new ArrayList<>();

    public Orden() {}

    public Orden(Usuario usuario, double total) {
        this.usuario = usuario;
        this.total = total;
        this.fecha = LocalDateTime.now();
    }

    public void addItem(OrdenItem item) {
        items.add(item);
    }

    public Long getId() { return id; }
}