package com.example.ativooperante_be.db.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ativooperante_be.db.entidades.Orgao;

public interface OrgaosRepository  extends JpaRepository<Orgao,Long> {
    
}
