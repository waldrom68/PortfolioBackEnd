// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTODegree;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Degree;
import com.portfolio.wdr.service.IDegreeService;
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
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Niveles de formacion educativa, formal e informal
// Recibe las peticiones y delega el negocio (es el pivot de la aplicacion)

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://portfolio-frontend-wdr.web.app"})
@RequestMapping("/degree")
public class ControllerDegree {

    @Autowired
    private IDegreeService objetoServ;
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> editarObjetoDegree(@RequestBody Degree data) {
        
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null
                && !objetoServ.existeInPerson(data.getName(), data.getPerson().getId(), data)) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        try {
            objetoServ.crearDegree(data);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/new")
    public ResponseEntity<?> crearDegree(@RequestBody Degree data) {
        
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        
        try {
            Degree nuevoObjeto = objetoServ.crearDegree(data);
            DTODegree temp = new DTODegree();
            temp.setId(nuevoObjeto.getId());
            temp.setName(nuevoObjeto.getName());
            
            return new ResponseEntity(temp, HttpStatus.OK);
     
//            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> borrarDegree(@PathVariable Long id) {
//PENDIENTE VERIFICAR EXISTENCIA EN LA RELACIONES ANTES DE INTENTAR BORRAR
        try {
            objetoServ.borrarDegree(id);
            return new ResponseEntity(new Mensaje("Degree eliminado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse el Degree, verifique que no existan Estudios con este Nivel de formación asociado"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list/{id}")
    public List<DTODegree> verByPersonId(@PathVariable Long id) {

        return objetoServ.verByPersonId(id);

    }
}

//    @GetMapping ("/list/degree/all")
//    public List<Degree> verDegree() {
//        return degreeRepo.verDegree();
//    }