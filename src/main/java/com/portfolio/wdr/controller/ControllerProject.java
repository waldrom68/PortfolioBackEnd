// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTOProject;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Project;
import com.portfolio.wdr.service.IProjectService;
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
@RequestMapping("/project")
public class ControllerProject {

    //    Creamos la dependencia con el servicio
    @Autowired
    private IProjectService projServ;

    @PostMapping("/edit")  // edit and create
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearProject(@RequestBody Project proj) {
        try {
            projServ.crearProject(proj);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> borrarProject(@PathVariable Long id) {
        try {
            projServ.borrarProject(id);
            return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse el Proyecto, verifique el ID o que no hayan Imagenes del Proyecto asociados"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DTOProject> verByPerson(@PathVariable Long id) {

        return projServ.verByPersonId(id);

    }

}

//    @GetMapping ("/list/project/all")
//    public List<Project> verProject() {
//    
//        return projServ.verProject();
//    
//    }
