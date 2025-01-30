package com.example.ativooperante_be.db.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ativooperante_be.db.entidades.Tipo;

public interface TipoRepository  extends JpaRepository<Tipo,Long> {
    
}
