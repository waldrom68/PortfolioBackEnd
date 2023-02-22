// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOFullPerson;

public interface IFullPersonService {
    
    public boolean existsPersonById(Long id);
    public DTOFullPerson verPersona(Long id);
    
}
