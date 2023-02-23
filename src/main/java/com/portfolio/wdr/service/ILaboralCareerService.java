// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOLaboralCareer;
import com.portfolio.wdr.model.LaboralCareer;
import java.util.List;


public interface ILaboralCareerService {
    
    public LaboralCareer crearLaboralCareer(LaboralCareer labor);
    public void borrarLaboralCareer(Long id);
    public LaboralCareer buscarLaboralCareer(Long id);
        
    public LaboralCareer findByResumeAndPersonId(String nombre, Long id);
    public boolean existeInPerson(String nombre, Long id, LaboralCareer objeto);
    
    public List<LaboralCareer> verLaboralCareer();
    public List<DTOLaboralCareer> verByPersonId(Long id);
    
}
