// Orden de creacion 3.-

package com.portfolio.wdr.repository;

import com.portfolio.wdr.model.SocialNetwork;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Long>{
    
       List<SocialNetwork> findByPersonId(Long Id);
       SocialNetwork findByNameAndPersonId(String nombre, Long id);
       
}
