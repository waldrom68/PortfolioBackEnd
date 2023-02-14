// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Degree;
import com.portfolio.wdr.service.IDegreeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Recibe las peticiones y delega el negocio (es el pivot de la aplicacion)
@RestController
@RequestMapping("/degree")
public class ControllerDegree {

    @Autowired
    private IDegreeService degreeRepo;

    @PostMapping("/edit")
    public ResponseEntity<?> crearDegree(@RequestBody Degree degree) {
        try {
            Degree temp = degreeRepo.crearDegree(degree);
            return new ResponseEntity(new Mensaje("Degree '" + temp.getName() +"' guardado"), HttpStatus.CREATED);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> borrarDegree(@PathVariable Long id) {

        try {
            degreeRepo.borrarDegree(id);
            return new ResponseEntity(new Mensaje("Degree eliminado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse el Degree"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    public List<Degree> verByPersonId(@PathVariable Long id) {

        return degreeRepo.verByPersonId(id);

    }
}

//    @GetMapping ("/list/degree/all")
//    public List<Degree> verDegree() {
//        return degreeRepo.verDegree();
//    }