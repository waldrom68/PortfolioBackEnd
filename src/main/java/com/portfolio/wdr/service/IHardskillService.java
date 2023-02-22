// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOHardskill;
import com.portfolio.wdr.model.Hardskill;
import java.util.List;


public interface IHardskillService {
    
    public Hardskill crearHard(Hardskill soft);
    public void borrarHard(Long id);
    public Hardskill buscarHard(Long id);
    
    public Hardskill findByNameAndPersonId(String nombre, Long id);
    public boolean existeSoftInPerson(String nombre, Long id, Hardskill objeto);
    
    public List<Hardskill> verHard();
    public List<DTOHardskill> verByPersonId(Long id);
    
}
