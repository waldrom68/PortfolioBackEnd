// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTOSoftskill;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Softskill;
import com.portfolio.wdr.service.ISoftskillService;
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
@RequestMapping("/softskill")
public class ControllerSoftskill {

    //    Creamos la dependencia con el servicio
    @Autowired
    private ISoftskillService objetoServ;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit")  // edit
    public ResponseEntity<?> editarObjetoSoft(@RequestBody Softskill data) {
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null && 
                !objetoServ.existeSoftInPerson(data.getName(), data.getPerson().getId(), data) ) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        try {
            objetoServ.crearSoft(data);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/new")
    public ResponseEntity<?> crearObjetoSoft(@RequestBody Softskill data) {
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null ) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        try {
            Softskill nuevoObjeto = objetoServ.crearSoft(data);
            DTOSoftskill temp = new DTOSoftskill();
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
    public ResponseEntity<?> borrarObjetoSoft(@PathVariable Long id) {
        try {
            objetoServ.borrarSoft(id);
            return new ResponseEntity(new Mensaje("Softskill eliminado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse el Softskill, verifique el ID"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DTOSoftskill> verByPerson(@PathVariable Long id) {

        return objetoServ.verByPersonId(id);

    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/reorder")
    public ResponseEntity<?> reorderEntity(@RequestBody List<Softskill> data) {

        try {
            for (Softskill elemento : data) {
                this.editarObjetoSoft(elemento);
                System.out.println("Hice el proceso para" + elemento);
            }
            return new ResponseEntity(new Mensaje("Orden de los softskill actualizado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
