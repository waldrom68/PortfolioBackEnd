// Orden de creacion 4.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOOrganization;
import com.portfolio.wdr.model.Organization;
import com.portfolio.wdr.model.Person;
import com.portfolio.wdr.repository.OrganizationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

@Service
public class OrganizationService implements IOrganizationService {

    @Autowired
    public OrganizationRepository orgaRepo;
    @Autowired public IPersonService persServ;
    
    @Override
    public Organization crearOrganizacion(Organization orga) {
        Long tmp_id = orga.getPerson().getId();
        Person pers = persServ.buscarPersona(tmp_id);
        if (pers != null ) {
            orga.setPerson(pers);
            
        } 

        return orgaRepo.save(orga);
    }

    @Override
    public void borrarOrganizacion(Long id) {
        orgaRepo.deleteById(id);
    }

    @Override
    public Organization buscarOrganizacion(Long id) {
        return orgaRepo.findById(id).orElse(null);
    }
    
    @Override
    public List<Organization> verOrganizacion() {
        return orgaRepo.findAll(Sort.by("name"));
    }

    @Override
    public List<DTOOrganization> verByPersonId(Long id) {
        List<Organization> listorga = orgaRepo.findByPersonId(id);
        List listatemp = new ArrayList();
        
        for (Organization elemento: listorga) {
            DTOOrganization tempDTO = new DTOOrganization();
            
            tempDTO.setId(elemento.getId());
            tempDTO.setName(elemento.getName());
            tempDTO.setResume(elemento.getResume());
            tempDTO.setUrl(elemento.getUrl());
            
            listatemp.add(tempDTO);
        
                   
        }
        
        return listatemp;
     }

    @Override
    public Organization findByNameAndPersonId(String nombre, Long id) {
        return orgaRepo.findByNameAndPersonId(nombre, id);

    }

    @Override
    public boolean existeInPerson(String nombre, Long id, Organization objeto) {
        Organization temp = orgaRepo.findByNameAndPersonId(nombre, id);
        if (temp != null) {
            return Objects.equals(temp.getId(), objeto.getId());
        } else {
            return false;
        }
    }
}
