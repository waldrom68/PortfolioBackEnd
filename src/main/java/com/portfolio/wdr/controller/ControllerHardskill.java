// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTOHardskill;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Hardskill;
import com.portfolio.wdr.service.IHardskillService;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import java.util.Optional;
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
    private IHardskillService hardServ;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/ver")
    public ResponseEntity<?>  buscarPorNombre(@RequestBody Hardskill hard) {
        Optional<Hardskill> temp = hardServ.findBynameAndPersonId(hard.getName(), hard.getPerson().getId(), hard);
        if (temp != null && temp.get().getId() == hard.getId())  {
            return new ResponseEntity(temp,HttpStatus.OK) ;
        }
        return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit")  // edit and create
    public ResponseEntity<?> crearHard(@RequestBody Hardskill hard) {
        if (StringUtils.isBlank(hard.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (hardServ.findBynameAndPersonId(hard.getName(), hard.getPerson().getId(), hard) == null ) {
           
            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        try {
            hardServ.crearHard(hard);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/new")
    public ResponseEntity<?> nuevoHard(@RequestBody Hardskill hard) {
        if (StringUtils.isBlank(hard.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (hardServ.findBynameAndPersonId(hard.getName(), hard.getPerson().getId(), hard) == null) {
           
            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        
        DTOHardskill temp = new DTOHardskill();
        Hardskill hardskill = hardServ.crearHard(hard);
        temp.setId(hardskill.getId());
        temp.setName(hardskill.getName());
        temp.setOrderdeploy(hardskill.getOrderdeploy());
        return new ResponseEntity(temp, HttpStatus.CREATED);

    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> borrarHard(@PathVariable Long id) {

        try {
            hardServ.borrarHard(id);
            return new ResponseEntity(new Mensaje("Hardskill eliminado"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse el Hardskill"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list/{id}")
    public List<DTOHardskill> verByPerson(@PathVariable Long id) {

        return hardServ.verByPersonId(id);

    }

}

//    @GetMapping ("/list/all")
//    public List<Hardskill> verHard() {
//    
//        return hardServ.verHard();
//    
//    }

