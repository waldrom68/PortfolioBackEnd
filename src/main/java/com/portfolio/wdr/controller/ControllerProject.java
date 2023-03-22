// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTOProject;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Project;
import com.portfolio.wdr.service.IProjectService;
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
@RequestMapping("/project")
public class ControllerProject {

    //    Creamos la dependencia con el servicio
    @Autowired
    private IProjectService objetoServ;

    @PostMapping("/edit")  // edit and create
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editarObjetoProj(@RequestBody Project data) {
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null && 
                !objetoServ.existeSoftInPerson(data.getName(), data.getPerson().getId(), data) ) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        try {
            objetoServ.crearProject(data);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/new")
    public ResponseEntity<?> crearObjetoProj(@RequestBody Project data) {
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null ) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        try {
            Project nuevoObjeto = objetoServ.crearProject(data);
            DTOProject temp = new DTOProject();
            temp.setId(nuevoObjeto.getId());
            temp.setName(nuevoObjeto.getName());
            temp.setResume(nuevoObjeto.getResume());
            
            temp.setSince(nuevoObjeto.getSince());
            
            temp.setUrl(nuevoObjeto.getUrl());
            temp.setOrderdeploy(nuevoObjeto.getOrderdeploy());

            return new ResponseEntity(temp, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> borrarProjectProj(@PathVariable Long id) {
        try {
            objetoServ.borrarProject(id);
            return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse el Proyecto, verifique el ID o que no hayan Imagenes del Proyecto asociados"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DTOProject> verByPerson(@PathVariable Long id) {

        return objetoServ.verByPersonId(id);

    }

}

//    @GetMapping ("/list/project/all")
//    public List<Project> verProject() {
//    
//        return projServ.verProject();
//    
//    }
