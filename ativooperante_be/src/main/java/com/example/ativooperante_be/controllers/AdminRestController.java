package com.example.ativooperante_be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ativooperante_be.db.entidades.Denuncia;
import com.example.ativooperante_be.db.entidades.Feedback;
import com.example.ativooperante_be.db.entidades.Orgao;
import com.example.ativooperante_be.db.entidades.Tipo;
import com.example.ativooperante_be.db.repositorios.DenunciaRepository;
import com.example.ativooperante_be.db.repositorios.FeedbackRepository;
import com.example.ativooperante_be.db.repositorios.OrgaosRepository;
import com.example.ativooperante_be.db.repositorios.TipoRepository;

@CrossOrigin // Aqui é a cors, para conectar o back com o front
@RestController
@RequestMapping("apis/admin")
public class AdminRestController {
    @Autowired
    TipoRepository tipoRepo;
    @Autowired 
    OrgaosRepository orgaosRepo;
    @Autowired
    DenunciaRepository denunciaRepo;
    @Autowired
    FeedbackRepository feedbackRepo;

    // INICIO - TIPO
    @GetMapping(value="get_tipos")
    public ResponseEntity<Object> getTipos() {

        return ResponseEntity.ok().body(tipoRepo.findAll(Sort.by("id"))); // o Sort é para listar ordenado
    }

    @GetMapping(value="get_tipo/{id}") // Pegar tipo pelo id
    public ResponseEntity<Object> getTipo(@PathVariable int id) {

        return ResponseEntity.ok().body(tipoRepo.findById((long) id));
    }

    @GetMapping(value="del_tipo/{id}") // Deletar o tipo pelo id
    public ResponseEntity<Object> delTipo(@PathVariable int id) {
        if (tipoRepo.existsById((long) id)) {

            tipoRepo.deleteById((long) id);
            return ResponseEntity.ok().body("Deletado com succeso");
        }
        return ResponseEntity.badRequest().body("Erro ao deletar");
    }

    @PostMapping(value="add_tipo") //Gravar o TIPO
    public ResponseEntity<Object> addTipo (@RequestBody Tipo tipo) {
        
        return  ResponseEntity.ok().body(tipoRepo.save(tipo));
    }

    @PostMapping(value="upd_tipo") //Alterar o TIPO
    public ResponseEntity<Object> updateTipo (@RequestBody Tipo tipo) {
        System.out.println("--->"+tipo);
        return  ResponseEntity.ok().body(tipoRepo.save(tipo));
    }

    //FIM- TIPO


    //ORGAOS - INICIO
    @GetMapping(value="get_orgaos")
    public ResponseEntity<Object> getOrgaos() {
        
        return ResponseEntity.ok().body(orgaosRepo.findAll());
    }

    @PostMapping(value="add_orgaos") 
    public ResponseEntity<Object> addOrgao (@RequestBody Orgao orgao) {
        
        return  ResponseEntity.ok().body(orgaosRepo.save(orgao));
    }

    @GetMapping(value="get_orgao/{id}") 
    public ResponseEntity<Object> getOrgao(@PathVariable int id) {

        return ResponseEntity.ok().body(orgaosRepo.findById((long) id));
    }

    @GetMapping(value="del_orgao/{id}") 
    public ResponseEntity<Object> delOrgao(@PathVariable int id) {
        if (orgaosRepo.existsById((long) id)) {

            orgaosRepo.deleteById((long) id);
            return ResponseEntity.ok().body("Deletado com succeso");
        }
        return ResponseEntity.badRequest().body("Erro ao deletar");
    }

    
    @PostMapping(value="upd_orgao") //Alterar o TIPO
    public ResponseEntity<Object> updateOrgao (@RequestBody Orgao orgao) {
        //System.out.println("--->"+tipo);
        return  ResponseEntity.ok().body(orgaosRepo.save(orgao));
    }


    //ORGAOS - FIM

    // ROTAS PARA DENUNCIAS
    @PostMapping(value="add_feedback_denuncia")
    public ResponseEntity<Object> addFeedbackDenuncia (@RequestBody Denuncia denuncia) {
        Feedback feedback = denuncia.getFeedback();
       feedback.setDenuncia(denuncia);
        return  ResponseEntity.ok().body(feedbackRepo.save(feedback));
    }

    @GetMapping(value="get_denuncias")
    public ResponseEntity<Object> getDenuncias() {
        
        return ResponseEntity.ok().body(denunciaRepo.findAll());
    }

    @GetMapping(value="del_denuncia/{id}") // Deletar o Denuncia pelo id
    public ResponseEntity<Object> delDenuncia(@PathVariable int id) {
        if (denunciaRepo.existsById((long) id)) {

            denunciaRepo.deleteById((long) id);
            return ResponseEntity.ok().body("Deletado com succeso");
        }
        return ResponseEntity.badRequest().body("Erro ao deletar");
    }

    @GetMapping(value="get_denuncia/{id}") 
    public ResponseEntity<Object> getDenuncia(@PathVariable int id) {

        return ResponseEntity.ok().body(denunciaRepo.findById((long) id));
    }

    
}

//http://localhost:8080/apis/admin/upd_tipo - alterar
//{ "id": 5 ,"nome":"Zoonoses"} - body e json

//http://localhost:8080/apis/admin/add_tipo - adicionar
//{ "nome":"Zoonoses"} - body e json
