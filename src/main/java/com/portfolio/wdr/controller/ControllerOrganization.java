// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTOOrganization;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Organization;
import com.portfolio.wdr.service.IOrganizationService;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Recibe las peticiones y delega el negocio (es el pivot de la aplicacion)
@RestController
@CrossOrigin(origins = "/**")
@RequestMapping("/organization")
public class ControllerOrganization {

    //    Creamos la dependencia con el servicio
    @Autowired
    private IOrganizationService objetoServ;

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editarorganization(@RequestBody Organization data) {
        
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null
                && !objetoServ.existeInPerson(data.getName(), data.getPerson().getId(), data)) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        try {
            objetoServ.crearOrganizacion(data);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/new")
    public ResponseEntity<?> crearorganization(@RequestBody Organization data) {
        
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        
        try {
            Organization nuevoObjeto = objetoServ.crearOrganizacion(data);
            DTOOrganization temp = new DTOOrganization();
            temp.setId(nuevoObjeto.getId());
            temp.setName(nuevoObjeto.getName());
            temp.setResume(nuevoObjeto.getResume());
            temp.setUrl(nuevoObjeto.getUrl());
            
            return new ResponseEntity(temp, HttpStatus.OK);
     
//            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
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
            objetoServ.borrarOrganizacion(id);
            return new ResponseEntity(new Mensaje("Organizacion eliminado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse la Organizacion, verifique que no existan Estudios o Trabajos con esta Organizacion asociada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/list/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DTOOrganization> verByPersonId(@PathVariable Long id) {

        return objetoServ.verByPersonId(id);

    }
}

//    @GetMapping ("/list/organization/all")
//    public List<Organization> verOrganization() {
//    
//        return orgaServ.verOrganizacion();
//    
//    }
