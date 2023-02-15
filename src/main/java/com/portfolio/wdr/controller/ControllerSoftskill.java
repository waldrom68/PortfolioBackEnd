// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTOSoftskill;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Softskill;
import com.portfolio.wdr.service.ISoftskillService;
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
@RequestMapping("/softskill")
public class ControllerSoftskill {

    //    Creamos la dependencia con el servicio
    @Autowired
    private ISoftskillService softServ;

    @PostMapping("/edit")  // edit and create
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearSoft(@RequestBody Softskill soft) {
        try {
            softServ.crearSoft(soft);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> borrarSoft(@PathVariable Long id) {
        try {
            softServ.borrarSoft(id);
            return new ResponseEntity(new Mensaje("Softskill eliminado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse el Softskill, verifique el ID"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DTOSoftskill> verByPerson(@PathVariable Long id) {

        return softServ.verByPersonId(id);

    }


//    @GetMapping ("/list/softskill/all")
//    public List<Softskill> verSoft() {
//    
//        return softServ.verSoft();
//    
//    }
//    @GetMapping ("/list/softskill/{puntaje}")
//    public List<Softskill> verByAssesment(@PathVariable int puntaje) {
//    
//        return softServ.verByAssesment(puntaje);
//    
//    }
}
