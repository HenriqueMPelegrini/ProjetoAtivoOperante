package com.example.ativooperante_be.db.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ativooperante_be.db.entidades.Denuncia;
import com.example.ativooperante_be.db.entidades.Usuario;

import java.util.List;


public interface DenunciaRepository  extends JpaRepository<Denuncia,Long> {
    public List<Denuncia> findAllByUsuario(Usuario usuario);

}
