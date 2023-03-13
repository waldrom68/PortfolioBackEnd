// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOfullPerson;

public interface IFullPersonService {
    
    public boolean existsPersonById(Long id);
    public DTOfullPerson verPersona(Long id);
    
}
