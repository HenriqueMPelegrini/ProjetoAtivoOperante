package com.example.ativooperante_be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.ativooperante_be.db.repositorios.UsuarioRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin
@RestController
public class UsuarioRestController {
    @Autowired
    private UsuarioRepository repo; 
    
    @GetMapping("usuarios")
    public ResponseEntity<Object> getUsuarios(){
        return ResponseEntity.ok(repo.findByEmail("admin@pm.br"));
    }
}
