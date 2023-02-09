// Orden de creacion 5.-

package com.portfolio.wdr.controller;

import com.portfolio.wdr.DTO.DTOProject;
import com.portfolio.wdr.model.Project;
import com.portfolio.wdr.service.IProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Recibe las peticiones y delega el negocio (es el pivot de la aplicacion)
@RestController
public class ControllerProject {
    
    //    Creamos la dependencia con el servicio
    @Autowired 
    private IProjectService projServ;
    
    
    @PostMapping ("/edit/project")  // edit and create
    public void crearProject (@RequestBody Project proj) {
    
        projServ.crearProject(proj);

    }
    
    @DeleteMapping ("/del/project/{id}")
    public void borrarProject (@PathVariable Long id) {
    
        projServ.borrarProject(id);
        
    }
    
//    @GetMapping ("/list/project/all")
//    public List<Project> verProject() {
//    
//        return projServ.verProject();
//    
//    }

    @GetMapping ("/list/project/{id}")
    public List<DTOProject> verByPerson(@PathVariable Long id) {
    
        return projServ.verByPersonId(id);
    
    }

}
