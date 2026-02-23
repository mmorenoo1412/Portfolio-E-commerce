package com.example.m6l2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.m6l2.modelo.Producto;

@Repository
public class ProductoDAOJdbc {

	@Autowired
    private final JdbcTemplate jdbcTemplate;

    public ProductoDAOJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Producto> rowMapper = (rs, rowNum) -> 
        new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));

    public List<Producto> listar() {
        return jdbcTemplate.query("SELECT * FROM productos", rowMapper);
    }

    public Producto buscarPorId(int id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM productos WHERE id = ?",
            new Object[]{id},
            rowMapper
        );
    }

    public void agregar(Producto producto) {
        jdbcTemplate.update(
            "INSERT INTO productos(nombre, precio) VALUES (?, ?)",
            producto.getNombre(), producto.getPrecio()
        );
    }
}

