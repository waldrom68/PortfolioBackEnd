// Orden de creacion 5.-
package com.portfolio.wdr.controller;

// Recibe las peticiones y delega el negocio (es el pivot de la aplicacion)
import com.portfolio.wdr.DTO.DTOPhone;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Phone;
import com.portfolio.wdr.service.IPhoneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "/**")
@RequestMapping("/phone")
public class ControllerPhone {

    //    Creamos la dependencia con el servicio
    @Autowired
    private IPhoneService phoneServ;

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearPhone(@RequestBody Phone phone) {
        try {
            phoneServ.crearPhone(phone);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/del/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> borrarPhone(@PathVariable Long id) {
        try {
            phoneServ.borrarPhone(id);
            return new ResponseEntity(new Mensaje("Telefono eliminado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse el Telefono"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/list/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DTOPhone> verByPerson(@PathVariable Long id) {

        return phoneServ.verByPersonId(id);

    }
}


//    @GetMapping ("/list/all")
//    public List<Phone> verPhone() {
//    
//        return phoneServ.verPhones();
//    
//    }
