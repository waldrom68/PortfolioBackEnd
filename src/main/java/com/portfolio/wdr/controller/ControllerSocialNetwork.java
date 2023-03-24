// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTOSocialNetwork;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.SocialNetwork;
import com.portfolio.wdr.service.ISocialnetworkService;
import java.util.List;
import io.micrometer.common.util.StringUtils;

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
@CrossOrigin(origins = {"http://localhost:4200", "https://portfolio-frontend-wdr.web.app"})
@RequestMapping("/socialnetwork")
public class ControllerSocialNetwork {

    //    Creamos la dependencia con el servicio
    @Autowired
    private ISocialnetworkService objetoServ;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> editarObjetoSocial(@RequestBody SocialNetwork social) {
        if (StringUtils.isBlank(social.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(social.getName(), social.getPerson().getId()) != null
                && !objetoServ.existeSoftInPerson(social.getName(), social.getPerson().getId(), social)) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        try {
            objetoServ.crearSocial(social);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/new")
    public ResponseEntity<?> crearObjetoSocial(@RequestBody SocialNetwork social) {
        if (StringUtils.isBlank(social.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByNameAndPersonId(social.getName(), social.getPerson().getId()) != null) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        try {
            SocialNetwork nuevoObjeto = objetoServ.crearSocial(social);
            
            DTOSocialNetwork temp = new DTOSocialNetwork();
            
            temp.setId(nuevoObjeto.getId());
            temp.setName(nuevoObjeto.getName());
            temp.setOrderdeploy(nuevoObjeto.getOrderdeploy());
            temp.setIconname(nuevoObjeto.getIconname());
            temp.setUrl(nuevoObjeto.getUrl());
            System.out.println(nuevoObjeto.toString());

            return new ResponseEntity(temp, HttpStatus.OK);
//              return new ResponseEntity(nuevoObjeto, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> borrarObjetoSocial(@PathVariable Long id) {
        try {
            objetoServ.borrarSocial(id);
            return new ResponseEntity(new Mensaje("Red Social eliminada"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse la Red Social, verifique el ID"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list/{id}")
    public List<DTOSocialNetwork> verByPerson(@PathVariable Long id) {

        return objetoServ.verByPersonId(id);

    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/reorder")
    public ResponseEntity<?> reorderEntity(@RequestBody List<SocialNetwork> data) {

        try {
            for (SocialNetwork elemento : data) {
                this.editarObjetoSocial(elemento);
                System.out.println("Hice el proceso para" + elemento);
            }
            return new ResponseEntity(new Mensaje("Orden de las redes actualizado"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

//    @GetMapping ("/list/social/all")
//    public List<SocialNetwork> verSocial() {
//    
//    return socialServ.verSocial();
//        
//    }
