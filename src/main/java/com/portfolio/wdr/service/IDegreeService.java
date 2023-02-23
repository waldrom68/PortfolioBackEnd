// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTODegree;
import com.portfolio.wdr.model.Degree;
import java.util.List;


public interface IDegreeService {
    
    public Degree crearDegree(Degree deg);
    public void borrarDegree(Long id);
    public Degree buscarDegree(Long id);
    
    public Degree findByNameAndPersonId(String nombre, Long id);
    public boolean existeInPerson(String nombre, Long id, Degree objeto);
    
    public List<DTODegree> verByPersonId(Long id);
    
}
