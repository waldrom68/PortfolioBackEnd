// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOHardskill;
import com.portfolio.wdr.model.Hardskill;
import java.util.List;
import java.util.Optional;


public interface IHardskillService {
    
    public Hardskill crearHard(Hardskill soft);
    public void borrarHard(Long id);
    public Hardskill buscarHard(Long id);
    public Optional<Hardskill> findBynameAndPersonId(String nombre, Long id, Hardskill hard);
    
    public List<Hardskill> verHard();
    public List<DTOHardskill> verByPersonId(Long id);
    
}
