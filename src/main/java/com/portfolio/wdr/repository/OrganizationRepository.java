// Orden de creacion 3.-

package com.portfolio.wdr.repository;

import com.portfolio.wdr.model.Organization;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    
    List<Organization> findByPersonId(Long Id);
    public Organization findByNameAndPersonId(String nombre, Long id);
    
}
