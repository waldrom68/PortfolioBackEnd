// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTORolePosition;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.RolePosition;
import com.portfolio.wdr.service.IRolePositionService;
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
//@CrossOrigin(origins = {"http://localhost:4200", "https://portfolio-frontend-wdr.web.app"})
@RequestMapping("/roleposition")
public class ControllerRolePosition {

    //    Creamos la dependencia con el servicio
    @Autowired
    private IRolePositionService objetoServ;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> editRolePosition(@RequestBody RolePosition data) {
        
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null
                && !objetoServ.existeInPerson(data.getName(), data.getPerson().getId(), data)) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        try {
            objetoServ.crearRolePosition(data);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/new")
    public ResponseEntity<?> crearRolePosition(@RequestBody RolePosition data) {
        
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        
        try {
            RolePosition nuevoObjeto = objetoServ.crearRolePosition(data);
            DTORolePosition temp = new DTORolePosition();
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
    public ResponseEntity<?> borrarRoleposition(@PathVariable Long id) {
//PENDIENTE VERIFICAR EXISTENCIA EN LA RELACIONES ANTES DE INTENTAR BORRAR
        try {
            objetoServ.borrarRolePosition(id);
            return new ResponseEntity(new Mensaje("Posicion/Rol eliminado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse la Posicion/Rol, verifique que no existan Experiencias laboral con esta Posicion/Rol asociado"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list/{id}")
    public List<DTORolePosition> verByPersonId(@PathVariable Long id) {

        return objetoServ.verByPersonId(id);

    }
}


//    @GetMapping ("/list/roleposition/all")
//    public List<RolePosition> verRolePosition() {
//    
//        return persoServ.verRolePosition();
//    
//    }
