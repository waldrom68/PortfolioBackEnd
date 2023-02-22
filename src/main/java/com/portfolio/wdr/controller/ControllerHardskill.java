// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTOHardskill;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Hardskill;
import com.portfolio.wdr.service.IHardskillService;
import io.micrometer.common.util.StringUtils;
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

// Recibe las peticiones y delega el negocio (es el pivot de la aplicacion)
@RequestMapping("/hardskill")
@RestController
public class ControllerHardskill {

    //    Creamos la dependencia con el servicio
    @Autowired
    private IHardskillService objetoServ;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit")  // edit
    public ResponseEntity<?> editarObjetoHard(@RequestBody Hardskill data) {
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null
                && !objetoServ.existeSoftInPerson(data.getName(), data.getPerson().getId(), data)) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        try {
            objetoServ.crearHard(data);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/new")
    public ResponseEntity<?> crearObjetoHard(@RequestBody Hardskill data) {
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        try {
            Hardskill nuevoObjeto = objetoServ.crearHard(data);
            DTOHardskill temp = new DTOHardskill();
            temp.setId(nuevoObjeto.getId());
            temp.setName(nuevoObjeto.getName());
            temp.setOrderdeploy(nuevoObjeto.getOrderdeploy());
            temp.setAssessment(nuevoObjeto.getAssessment());

            return new ResponseEntity(temp, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> borrarObjetoHard(@PathVariable Long id) {
        try {
            objetoServ.borrarHard(id);
            return new ResponseEntity(new Mensaje("Hardskill eliminado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse el Hardskill, verifique el ID"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DTOHardskill> verByPerson(@PathVariable Long id) {

        return objetoServ.verByPersonId(id);

    }
//    @GetMapping ("/list/all")
//    public List<Hardskill> verHard() {
//    
//        return hardServ.verHard();
//    
}
