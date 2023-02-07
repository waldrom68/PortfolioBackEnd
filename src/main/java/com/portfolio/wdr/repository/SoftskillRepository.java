// Orden de creacion 3.-

package com.portfolio.wdr.repository;


import com.portfolio.wdr.model.Softskill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftskillRepository extends JpaRepository<Softskill, Long> {
    
    List<Softskill> findByPersonId(Long Id);
    List<Softskill> findByAssessment(int puntaje);
    
}

