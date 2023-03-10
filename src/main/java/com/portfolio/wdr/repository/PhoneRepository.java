// Orden de creacion 3.-

package com.portfolio.wdr.repository;

import com.portfolio.wdr.model.Phone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository <Phone, Long>{
        List<Phone> findByPersonId(Long Id);
}
