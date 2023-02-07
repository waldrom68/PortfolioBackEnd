// Orden de creacion 3.-

package com.portfolio.wdr.repository;

import com.portfolio.wdr.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
