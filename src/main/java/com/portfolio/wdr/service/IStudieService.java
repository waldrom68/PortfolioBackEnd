// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOStudie;
import com.portfolio.wdr.model.Studie;
import java.util.List;


public interface IStudieService {
    
    public Studie crearStudie(Studie studie);
    public void borrarStudie(Long id);
    public Studie buscarStudie(Long id);
    
    public Studie findByNameAndPersonId(String nombre, Long id);
    public boolean existeInPerson(String nombre, Long id, Studie objeto);
    
//    public List<Studie> verStudie();
    public List<DTOStudie> verByPersonId(Long id);
    
}
