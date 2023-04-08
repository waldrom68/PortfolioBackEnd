// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTOLaboralCareer;
import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.LaboralCareer;
import com.portfolio.wdr.service.ILaboralCareerService;
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
@CrossOrigin(origins = {"http://localhost:4200", "https://portfolio-frontend-wdr.web.app"})
@RequestMapping("/laboralcareer")
public class ControllerLaboralCareer {

    //    Creamos la dependencia con el servicio
    @Autowired
    private ILaboralCareerService objetoServ;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> editarLaboralCareer(@RequestBody LaboralCareer data) {

        if (StringUtils.isBlank(data.getResume())) {
            return new ResponseEntity(new Mensaje("El resumen es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByResumeAndPersonId(data.getResume(), data.getPerson().getId()) != null
                && !objetoServ.existeInPerson(data.getResume(), data.getPerson().getId(), data)) {

            return new ResponseEntity(new Mensaje("El resumen ya existe"), HttpStatus.BAD_REQUEST);
        }

        try {
            objetoServ.crearLaboralCareer(data);
            return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/new")
    public ResponseEntity<?> crearLaboralCareer(@RequestBody LaboralCareer data) {

        if (StringUtils.isBlank(data.getResume())) {
            return new ResponseEntity(new Mensaje("El resumen es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (objetoServ.findByResumeAndPersonId(data.getResume(), data.getPerson().getId()) != null 
                && !objetoServ.existeInPerson(data.getResume(), data.getPerson().getId(), data)) {

            return new ResponseEntity(new Mensaje("El resumen ya existe"), HttpStatus.BAD_REQUEST);
        }

        try {
            LaboralCareer nuevoObjeto = objetoServ.crearLaboralCareer(data);
            DTOLaboralCareer temp = new DTOLaboralCareer();
            temp.setId(nuevoObjeto.getId());
            temp.setResume(nuevoObjeto.getResume());
            temp.setStartDate(nuevoObjeto.getStartDate());
            temp.setEndDate(nuevoObjeto.getEndDate());
            temp.setStatus(true);
            temp.setOrderdeploy(nuevoObjeto.getOrderdeploy());
            temp.setOrganization(nuevoObjeto.getOrganization());
            temp.setRoleposition(nuevoObjeto.getRoleposition());

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
    public ResponseEntity<?> borrarLaboralCareer(@PathVariable Long id) {
        try {
            objetoServ.borrarLaboralCareer(id);
            return new ResponseEntity(new Mensaje("Eliminado correctamente"), HttpStatus.OK);
//        } catch (DataAccessException e) {
//            return new ResponseEntity(new Mensaje("No pudo guardarse el Degree, problema con los datos"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo eliminarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list/{id}")
    public List<DTOLaboralCareer> verByPersonId(@PathVariable Long id
    ) {

        return objetoServ.verByPersonId(id);

    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/reorder")
    public ResponseEntity<?> reorderEntity(@RequestBody List<LaboralCareer> data) {

        try {
            for (LaboralCareer elemento : data) {
                this.editarLaboralCareer(elemento);
                System.out.println("Hice el proceso para" + elemento);
            }
            return new ResponseEntity(new Mensaje("Orden de la trayectoria actualizada"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}





//    @GetMapping ("/list/laboralcareer/all")
//    public List<LaboralCareer> verLaboralCareer() {
//    
//        return laboralServ.verLaboralCareer();
//    
//    }
