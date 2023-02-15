// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Organization;
import com.portfolio.wdr.service.IOrganizationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Recibe las peticiones y delega el negocio (es el pivot de la aplicacion)
@RestController
@RequestMapping("/organization")
public class ControllerOrganization {

    //    Creamos la dependencia con el servicio
    @Autowired
    private IOrganizationService orgaServ;

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearorganization(@RequestBody Organization orga) {
        try {
            orgaServ.crearOrganizacion(orga);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> borrarOrganization(@PathVariable Long id) {

        try {
            orgaServ.borrarOrganizacion(id);
            return new ResponseEntity(new Mensaje("Organizacion eliminado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse la Organizacion, verifique el ID o que no hayan Estudios o Trabajos con esta Organizacion asociada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/list/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Organization> verByPersonId(@PathVariable Long id) {

        return orgaServ.verByPersonId(id);

    }
}

//    @GetMapping ("/list/organization/all")
//    public List<Organization> verOrganization() {
//    
//        return orgaServ.verOrganizacion();
//    
//    }
