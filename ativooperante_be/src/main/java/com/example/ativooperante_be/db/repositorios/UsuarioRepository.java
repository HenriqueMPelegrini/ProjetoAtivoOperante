package com.example.ativooperante_be.db.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ativooperante_be.db.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{ //MESMA COISA QUE DAO
    
    public Usuario findByEmail(String email); // ele sabe que vai buscar o email pelo nome "findEmail"

}
