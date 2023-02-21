// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOInterest;
import com.portfolio.wdr.model.Interest;
import java.util.List;
import java.util.Optional;


public interface IInterestService {
    
   public Interest crearInteres(Interest inter);

   public void borrarInteres(Long id);
   public Interest buscarInteres(Long id);
   public Optional<Interest> findBynameAndPersonId(String nombre, Long id, Interest inter);
   
   public List<Interest> verIntereses();
   public List<DTOInterest> verByPersonId(Long id);
    
}
