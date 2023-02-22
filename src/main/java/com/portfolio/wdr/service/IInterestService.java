// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOInterest;
import com.portfolio.wdr.model.Interest;
import java.util.List;


public interface IInterestService {
    
   public Interest crearInteres(Interest inter);

   public void borrarInteres(Long id);
   public Interest buscarInteres(Long id);
   
    public Interest findByNameAndPersonId(String nombre, Long id);
    public boolean existeSoftInPerson(String nombre, Long id, Interest objeto);
   
   public List<Interest> verIntereses();
   public List<DTOInterest> verByPersonId(Long id);
    
}
