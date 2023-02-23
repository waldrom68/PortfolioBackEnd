// Orden de creacion 3

package com.portfolio.wdr.repository;

import com.portfolio.wdr.model.Degree;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {
    
    List<Degree> findByPersonId(Long Id);
    public Degree findByNameAndPersonId(String nombre, Long id);
    
}
