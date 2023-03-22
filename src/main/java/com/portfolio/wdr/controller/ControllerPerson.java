// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.DisplayData;
import com.portfolio.wdr.model.Person;
import com.portfolio.wdr.service.IDisplayDataService;
import com.portfolio.wdr.service.IPersonService;
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
// import org.springframework.web.bind.annotation.ResponseBody;

// Recibe las peticiones y delega el negocio (es el pivot de la aplicacion)
@RestController
@CrossOrigin(origins = {"http://localhost:4200/", "https://portfolio-frontend-wdr.web.app/"})
@RequestMapping("/person")
public class ControllerPerson {
//    List<Person> listaPersonas = new ArrayList();

//    Creamos la dependencia con el servicio
    @Autowired
    private IPersonService persoServ;
    @Autowired
    private IDisplayDataService displayServ;

    @GetMapping("/view/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> mostrarPersona(@PathVariable Long id) {
        if (persoServ.existsPersonById(id)) {
            return new ResponseEntity(persoServ.verPersona(id), HttpStatus.OK);
        }
        return new ResponseEntity(new Mensaje("No existe lo solicitado, verifique el ID"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/edit")  // edit and create Person
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearPersona(@RequestBody Person pers) {
//        System.out.println(pers.toString());
        Person persona = persoServ.buscarPersona(pers.getId());
        String respuesta = "";

        if (persona == null) {
            respuesta = "Creando una persona";
            // Creo la instancia DisplayData que tienen por default todas las personas
            DisplayData temp = new DisplayData();
            // Fuerzo su guardado en el repositorio para obtener el id asignado
            DisplayData guardada = displayServ.crearForceDisplay(temp);
            // Vinculo ambas instancias
            // Guardando la instancia con su respectiva relacion OneToOne con DisplayData
            pers.setDisplaydata(guardada);

        } else {

//            pers.setPassword(persona.getPassword());
            pers.setDisplaydata(persona.getDisplaydata());
            respuesta = "Actualizando todos los datos suministrados";

        }
        try {
            persoServ.crearPersona(pers);
            return new ResponseEntity(new Mensaje(respuesta), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

// Le quito esta funcionalidad, no va a dar altas ni bajas desde el frontEnd.
//    @GetMapping ("/del/person/{id}")
//    public void borrarPersona (@PathVariable Long id) {
//    
//        persoServ.borrarPersona(id);
//
//    }
// Le quito visibilidad, esta funcionalidad es para uso interno.    
//    @GetMapping ("/find/person/{id}")
//    public Person buscarPersona(@PathVariable Long id) {
//        System.out.println("En el servicio, busco una persona");
//        return persoServ.buscarPersona(id);
//    
//    }
}
