package com.example.ativooperante_be.db.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ativooperante_be.db.entidades.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback,Long>{
    
}
