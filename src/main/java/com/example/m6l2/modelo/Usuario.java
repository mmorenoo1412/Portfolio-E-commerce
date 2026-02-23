package com.example.m6l2.modelo;

import org.jspecify.annotations.Nullable;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String rol;
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	public String getRol() {
		// TODO Auto-generated method stub
		return rol;
	}

}