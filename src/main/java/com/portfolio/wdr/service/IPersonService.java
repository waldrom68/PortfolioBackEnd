// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOPerson;
import com.portfolio.wdr.model.Person;



public interface IPersonService {
    
    public boolean existsPersonById(Long id);
    public void borrarPersona(Long id);
    public Person buscarPersona(Long id);
    public Person crearPersona(Person per);
//    public void editPersona(Person per);
    public DTOPerson verPersona(Long id);
    
}
