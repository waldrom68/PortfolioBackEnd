// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTOStudie;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Studie;
import com.portfolio.wdr.service.IStudieService;
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
@RequestMapping("/studie")
public class ControllerStudie {

    //    Creamos la dependencia con el servicio
    @Autowired
    private IStudieService studieServ;

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearStudie(@RequestBody Studie studie) {
        try {
            studieServ.crearStudie(studie);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> borrarStudie(@PathVariable Long id) {
        try {
            studieServ.borrarStudie(id);
            return new ResponseEntity(new Mensaje("Estudio eliminado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse el Estudio, verifique el ID"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DTOStudie> verByPersonId(@PathVariable Long id) {

        return studieServ.verByPersonId(id);

    }
}

//    @RequestMapping("/hardskill")
//    @PreAuthorize("hasRole('ADMIN')")
//      ResponseEntity<?> 
//        try {
//            degreeRepo.crearDegree(degree);
//            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
////        } catch (DataAccessException e) {
////            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
//        } catch (Exception e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        try {
//            degreeRepo.borrarDegree(id);
//            return new ResponseEntity(new Mensaje("Degree eliminado"), HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity(new Mensaje("No pudo eliminarse el Degree, verifique el ID o que no hayan Estudios con este Degree registrado"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    @GetMapping ("/list/studie/all")
//    public List<Studie> verStudie() {
//    
//        return studieServ.verStudie();
//    
//    }

