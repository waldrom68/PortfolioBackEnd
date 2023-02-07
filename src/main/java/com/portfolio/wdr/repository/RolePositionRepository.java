// Orden de creacion 3.-

package com.portfolio.wdr.repository;

import com.portfolio.wdr.model.RolePosition;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolePositionRepository extends JpaRepository<RolePosition, Long>{
    
    List<RolePosition> findByPersonId(Long Id);
    
}
