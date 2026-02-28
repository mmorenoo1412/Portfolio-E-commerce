package com.example.m6l2.controller;

import com.example.m6l2.modelo.Usuario;
import com.example.m6l2.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistroController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registro")
    public String registroForm() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String username,
                                   @RequestParam String password,
                                   Model model) {
        if (usuarioRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "El usuario ya existe");
            return "registro";
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setRol("ROLE_USER");

        usuarioRepository.save(usuario);

        return "redirect:/login?message=Usuario registrado correctamente, ingresa para continuar";
    }
}