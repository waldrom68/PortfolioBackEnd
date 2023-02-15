// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.ProjectMedia;
import com.portfolio.wdr.service.IProjectMediaService;
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
@RequestMapping("/projectmedia")
public class ControllerProjectMedia {

    //    Creamos la dependencia con el servicio
    @Autowired
    private IProjectMediaService persoServ;

    @PostMapping("/new/projectmedia")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearProjectMedia(@RequestBody ProjectMedia pers) {
        try {
            persoServ.crearProjectMedia(pers);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/del/projectmedia")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> borrarProjectMedia(@PathVariable Long id) {
        try {
            persoServ.borrarProjectMedia(id);
            return new ResponseEntity(new Mensaje("Archivo multimedia eliminado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse el archivo multimedia, verifique el ID o que no hayan Estudios con este Degree registrado"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/projectmedia")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProjectMedia> verProjectMedia() {

        return persoServ.verProjectMedia();

    }
}
//    PENDIENTE BUSCAR SOLO LOS QUE PERTENENCEN AL ID DEL PROYECTO
//    @PutMapping ("/edit/projectmedia")
//    public void editarProjectMedia(@RequestBody ProjectMedia pers) {
//    
//        persoServ.crearProjectMedia(pers);
//
//    }
