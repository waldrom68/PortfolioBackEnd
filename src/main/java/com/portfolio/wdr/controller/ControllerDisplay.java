// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.DisplayData;
import com.portfolio.wdr.service.IDisplayDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Recibe las peticiones y delega el negocio (es el pivot de la aplicacion)
@RestController
@CrossOrigin(origins = "/**")
@RequestMapping("/displaydata")
public class ControllerDisplay {

    //    Creamos la dependencia con el servicio
    @Autowired
    private IDisplayDataService displayServ;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> editarDisplayData(@RequestBody DisplayData display) {

        try {
            displayServ.crearDisplay(display);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public List<DisplayData> verDisplayData() {

        return displayServ.verDisplay();

    }

}
