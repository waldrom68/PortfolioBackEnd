// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTOInterest;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Interest;
import com.portfolio.wdr.service.IInterestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/interest")
@RestController
public class ControllerInterest {

    //    Creamos la dependencia
    @Autowired
    private IInterestService interServ;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> crearInteres(@RequestBody Interest inter) {

        try {
            interServ.crearInteres(inter);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
// return interServ.crearInteres(inter);

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/new")
    public DTOInterest nuevoInteres(@RequestBody Interest inter) {
        DTOInterest temp = new DTOInterest();
        Interest interes = interServ.crearInteres(inter);
        temp.setId(interes.getId());
        temp.setName(interes.getName());
        temp.setOrderdeploy(interes.getOrderdeploy());
        return temp;

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> borrarInteres(@PathVariable Long id) {

        try {
            interServ.borrarInteres(id);
            return new ResponseEntity(new Mensaje("Eliminado correctamentee"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list/{id}")
    public List<DTOInterest> verByPerson(@PathVariable Long id) {

        return interServ.verByPersonId(id);

    }

}

//    @GetMapping ("/list/interest/all")
//    public List<Interest> listarInteres() {
//    
//        return interServ.verIntereses();
//    
//    }
