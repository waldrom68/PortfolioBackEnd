// Orden de creacion 2.-

package com.portfolio.wdr.service;


import com.portfolio.wdr.DTO.DTOSoftskill;
import com.portfolio.wdr.model.Softskill;
import java.util.List;


public interface ISoftskillService {
    
    public Softskill crearSoft(Softskill soft);
    public void borrarSoft(Long id);
    public Softskill buscarSoft(Long id);
    
    public Softskill findByNameAndPersonId(String nombre, Long id);
    public boolean existeSoftInPerson(String nombre, Long id, Softskill objeto);
    
    public List<Softskill> verSoft();
    public List<DTOSoftskill> verByPersonId(Long id);

}

// para una prueba
//    public List<Softskill> verByAssesment(int puntaje);