// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTOInterest;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Interest;
import com.portfolio.wdr.service.IInterestService;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://portfolio-frontend-wdr.web.app"})
@RequestMapping("/interest")
public class ControllerInterest {

    //    Creamos la dependencia
    @Autowired
    private IInterestService objetoServ;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> editarObjetoInter(@RequestBody Interest data) {
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null
                && !objetoServ.existeSoftInPerson(data.getName(), data.getPerson().getId(), data)) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        try {
            objetoServ.crearInteres(data);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
// return interServ.crearInteres(inter);

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/new")
    public ResponseEntity<?> crearObjetoInter(@RequestBody Interest data) {
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        try {
            Interest nuevoObjeto = objetoServ.crearInteres(data);
            DTOInterest temp = new DTOInterest();
            temp.setId(nuevoObjeto.getId());
            temp.setName(nuevoObjeto.getName());
            temp.setOrderdeploy(nuevoObjeto.getOrderdeploy());

            return new ResponseEntity(temp, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> borrarObjetoInter(@PathVariable Long id) {

        try {
            objetoServ.borrarInteres(id);
            return new ResponseEntity(new Mensaje("Eliminado correctamentee"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list/{id}")
    public List<DTOInterest> verByPerson(@PathVariable Long id) {

        return objetoServ.verByPersonId(id);

    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/reorder")
    public ResponseEntity<?> reorderEntity(@RequestBody List<Interest> data) {

        try {
            for (Interest elemento : data) {
                this.editarObjetoInter(elemento);
                System.out.println("Hice el proceso para" + elemento);
            }
            return new ResponseEntity(new Mensaje("Orden de los Interest actualizado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

//    @GetMapping ("/list/interest/all")
//    public List<Interest> listarInteres() {
//    
//        return interServ.verIntereses();
//    
//    }
