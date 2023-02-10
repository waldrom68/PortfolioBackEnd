// Orden de creacion 4.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOHardskill;
import com.portfolio.wdr.model.Hardskill;
import com.portfolio.wdr.model.Person;
import com.portfolio.wdr.repository.HardskillRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class HardskillService implements IHardskillService {

    // Para la conecion con el JPA : PersonRepository hara de intermediario entre
    // la DB y nuestros metodos, para ello deberemos inyectar nuestra dependencia
    @Autowired public HardskillRepository hardRepo;
    @Autowired public IPersonService persServ;

    @Override
    public Hardskill crearHard(Hardskill hard) {
        Long tmp_id = hard.getPerson().getId();
        Person pers = persServ.buscarPersona(tmp_id);
        
        if (pers != null) {
            hard.setPerson(pers);
        } 
        
        return hardRepo.save(hard);
    }

    @Override
    public void borrarHard(Long id) {
        hardRepo.deleteById(id);
    }

    @Override
    public Hardskill buscarHard(Long id) {
        return hardRepo.findById(id).orElse(null);
    }

    @Override
    public List<Hardskill> verHard() {
        return hardRepo.findAll(Sort.by("orderdeploy").ascending());
    }

    @Override
    public List<DTOHardskill> verByPersonId(Long id) {
        //        Person pers = persServ.buscarPersona(id);
        List<Hardskill> lista = hardRepo.findByPersonId(id);
        
        // Ordeno la lista obtenida
        lista.sort(Comparator.comparing(Hardskill::getOrderdeploy));
        
        List listatemp = new ArrayList();
        
        for (Hardskill elemento :lista) {
            DTOHardskill tempDTO = new DTOHardskill();
            
            tempDTO.setId(elemento.getId());
            tempDTO.setName(elemento.getName());
            tempDTO.setAssessment(elemento.getAssessment());
            tempDTO.setOrderdeploy(elemento.getOrderdeploy());
            
            listatemp.add(tempDTO);
        }
         
        
        return listatemp;

    }
    
    
}
