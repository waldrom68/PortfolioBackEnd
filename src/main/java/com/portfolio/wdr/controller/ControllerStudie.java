// Orden de creacion 5.-

package com.portfolio.wdr.controller;
import com.portfolio.wdr.DTO.DTOStudie;
import com.portfolio.wdr.model.Studie;
import com.portfolio.wdr.service.IStudieService;
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
public class ControllerStudie {
    //    Creamos la dependencia con el servicio
    @Autowired 
    private IStudieService studieServ;
    
        
    @PostMapping ("/edit/studie")
    public void crearStudie (@RequestBody Studie studie) {
        studieServ.crearStudie(studie);

    
    }
    
    @DeleteMapping ("/del/studie/{id}")
    public void borrarStudie (@PathVariable Long id) {
    
        studieServ.borrarStudie(id);
        
    }
    
          
//    @GetMapping ("/list/studie/all")
//    public List<Studie> verStudie() {
//    
//        return studieServ.verStudie();
//    
//    }
    
              
    @GetMapping ("/list/studie/{id}")
    public List<DTOStudie> verByPersonId(@PathVariable Long id) {
    
        return studieServ.verByPersonId(id);
    
    }
}
    