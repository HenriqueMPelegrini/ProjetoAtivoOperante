package com.example.ativooperante_be.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ativooperante_be.db.entidades.Denuncia;
import com.example.ativooperante_be.db.entidades.Usuario;
import com.example.ativooperante_be.db.repositorios.DenunciaRepository;
import com.example.ativooperante_be.db.repositorios.OrgaosRepository;
import com.example.ativooperante_be.db.repositorios.TipoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin
@RestController
@RequestMapping("apis/cidadao")
public class CidadaoRestController {
    @Autowired
    TipoRepository tipoRepo;
    @Autowired 
    OrgaosRepository orgaosRepo;
    @Autowired
    DenunciaRepository denunciaRepo;

    @GetMapping(value="get_tipos")
    public ResponseEntity<Object> getTipos() {

        return ResponseEntity.ok().body(tipoRepo.findAll());
    }

    @GetMapping(value="get_orgaos")
    public ResponseEntity<Object> getOrgaos() {
        
        return ResponseEntity.ok().body(orgaosRepo.findAll());
    }

    @GetMapping(value="get_denuncias/{id_usu}")
    public ResponseEntity<Object> getDenuncias(@PathVariable int id_usu) {
        Usuario us = new Usuario();
        us.setId((long) id_usu); // como é long tem que colocar o L
        return ResponseEntity.ok().body(denunciaRepo.findAllByUsuario(us));
    }


    @PostMapping(value="add_denuncia")
    public ResponseEntity<Object> addDenuncia (@RequestBody Denuncia denuncia) {
        return  ResponseEntity.ok().body(denunciaRepo.save(denuncia));
    }

   
    
    
    
}
