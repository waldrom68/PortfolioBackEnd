
package com.portfolio.wdr.repository;

import com.portfolio.wdr.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends JpaRepository<Card, Long>{
    
}
