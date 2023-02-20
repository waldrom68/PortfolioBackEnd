// Orden de creacion 5.-
package com.portfolio.wdr.controller;

// Recibe las peticiones y delega el negocio (es el pivot de la aplicacion)
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.service.IFullPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerFullPerson {

    //    Creamos la dependencia con el servicio
    @Autowired
    private IFullPersonService persoServ;

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/fullperson/view/{id}")
    public ResponseEntity<?> mostrarPersona(@PathVariable Long id) {
        if (persoServ.existsPersonById(id)) {
            return new ResponseEntity(persoServ.verPersona(id), HttpStatus.OK);
        }
        
        return new ResponseEntity(new Mensaje("No existe lo solicitado, verifique el ID"), HttpStatus.BAD_REQUEST);

    }
}
