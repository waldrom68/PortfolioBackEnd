// Orden de creacion 3.-

package com.portfolio.wdr.repository;

import com.portfolio.wdr.model.Hardskill;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardskillRepository extends JpaRepository<Hardskill, Long>{
        public List<Hardskill> findByPersonId(Long Id);
        public Optional<Hardskill> findBynameAndPersonId(String nombre, Long id);
}
