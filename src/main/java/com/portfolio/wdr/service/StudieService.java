// Orden de creacion 4.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOStudie;
import com.portfolio.wdr.model.Degree;
import com.portfolio.wdr.model.Organization;
import com.portfolio.wdr.model.Person;
import com.portfolio.wdr.model.Studie;
import com.portfolio.wdr.repository.StudieRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudieService implements IStudieService {

    // Para la conecion con el JPA : PersonRepository hara de intermediario entre
    // la DB y nuestros metodos, para ello deberemos inyectar nuestra dependencia
    @Autowired public StudieRepository studieRepo;
    @Autowired public IPersonService persServ;
    @Autowired public IDegreeService degreeServ;
    @Autowired public IOrganizationService orgaServ;
    

    @Override
    public Studie crearStudie(Studie studie) {

        Person pers = persServ.buscarPersona(studie.getPerson().getId());
        Organization orga = orgaServ.buscarOrganizacion(studie.getOrganization().getId());
        Degree degree = degreeServ.buscarDegree(studie.getDegree().getId());
        
        if (pers != null && orga != null && degree != null) {
            studie.setPerson(pers);
            studie.setOrganization(orga);
            studie.setDegree(degree);
            
        }
        
        return studieRepo.save(studie);
        

    }

    @Override
    public void borrarStudie(Long id) {
        studieRepo.deleteById(id);
    }

    @Override
    public Studie buscarStudie(Long id) {
        return studieRepo.findById(id).orElse(null);
    }
    


    @Override
    public List<DTOStudie> verByPersonId(Long id) {
        List<Studie> liststudie = studieRepo.findByPersonId(id);
        
        // Ordeno la lista obtenida
        liststudie.sort(Comparator.comparing(studie -> studie.getOrderdeploy()));
        
        List listatemp = new ArrayList();
        
        for (Studie elemento :liststudie) {
            DTOStudie tempDTO = new DTOStudie();
            
            tempDTO.setId(elemento.getId());
            tempDTO.setName(elemento.getName());
            
            String temp = elemento.getStartDate().toString();
            tempDTO.setStartDate(temp.substring(0, 10));
//            tempDTO.setStartDate(elemento.getStartDate());
            
            String temp2 = elemento.getEndDate().toString();
            tempDTO.setEndDate(temp2.substring(0, 10));
//            tempDTO.setEndDate(elemento.getEndDate());
            
            tempDTO.setOrderdeploy(elemento.getOrderdeploy());
            tempDTO.setStatus(elemento.isStatus());
            
            Organization tempOrga = elemento.getOrganization();
            tempOrga.setPerson(null);
            tempDTO.setOrganization(tempOrga);
            
            Degree tempDegree = elemento.getDegree();
            tempDegree.setPerson(null); // para que no envie estos datos
            tempDTO.setDegree(tempDegree);
        
            listatemp.add(tempDTO);
        
        
        }

        return listatemp;
        
    }

    
}
