// Orden de creacion 3.-

package com.portfolio.wdr.repository;

import com.portfolio.wdr.model.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    
        List<Project> findByPersonId(Long Id);
        
}
