// Orden de creacion 5.-

package com.portfolio.wdr.controller;

// Recibe las peticiones y delega el negocio (es el pivot de la aplicacion)

import com.portfolio.wdr.DTO.DTOfullPerson;
import com.portfolio.wdr.service.IFullPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerFullPerson {
    //    Creamos la dependencia con el servicio
    @Autowired 
    private IFullPersonService persoServ;
    
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/fullperson/view/{id}")
    public DTOfullPerson mostrarPersona(@PathVariable Long id) {
        
        return persoServ.verPersona(id);
        
    }
}
