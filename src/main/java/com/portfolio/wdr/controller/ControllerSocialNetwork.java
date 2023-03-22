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
    private ISocialnetworkService socialServ;

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearSocial(@RequestBody SocialNetwork social) {
        try {
            socialServ.crearSocial(social);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/new")
    public ResponseEntity<?> crearObjetoInter(@RequestBody SocialNetwork data) {
        if (StringUtils.isBlank(data.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (socialServ.findByNameAndPersonId(data.getName(), data.getPerson().getId()) != null) {

            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        try {
            SocialNetwork nuevoObjeto = socialServ.crearSocial(data);
            DTOSocialNetwork temp = new DTOSocialNetwork();
            temp.setId(nuevoObjeto.getId());
            temp.setName(nuevoObjeto.getName());
            temp.setOrderdeploy(nuevoObjeto.getOrderdeploy());
            temp.setPathIcon(nuevoObjeto.getPathIcon());
            temp.setUrl(nuevoObjeto.getUrl());

            return new ResponseEntity(temp, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> borrarSocial(@PathVariable Long id) {
        try {
            socialServ.borrarSocial(id);
            return new ResponseEntity(new Mensaje("Red Social eliminada"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse la Red Social, verifique el ID"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DTOSocialNetwork> verByPerson(@PathVariable Long id) {

        return socialServ.verByPersonId(id);

    }

}

//    @GetMapping ("/list/social/all")
//    public List<SocialNetwork> verSocial() {
//    
//    return socialServ.verSocial();
//        
//    }
