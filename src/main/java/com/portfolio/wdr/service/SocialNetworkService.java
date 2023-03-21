// Orden de creacion 4.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOSocialNetwork;
import com.portfolio.wdr.model.Person;
import com.portfolio.wdr.model.SocialNetwork;
import com.portfolio.wdr.repository.SocialNetworkRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SocialNetworkService implements ISocialnetworkService {

        // Para la conecion con el JPA : PersonRepository hara de intermediario entre
    // la DB y nuestros metodos, para ello deberemos inyectar nuestra dependencia
    @Autowired public SocialNetworkRepository socialRepo;
    @Autowired public IPersonService persServ;


    @Override
    public SocialNetwork crearSocial(SocialNetwork social) {
        Long tmp_id = social.getPerson().getId();
        Person pers = persServ.buscarPersona(tmp_id);
        
        if (pers != null) {
            social.setPerson(pers);
        
        }
        
        return socialRepo.save(social);
    }

    @Override
    public void borrarSocial(Long id) {
        socialRepo.deleteById(id);
    }

    @Override
    public SocialNetwork buscarSocial(Long id) {
        return socialRepo.findById(id).orElse(null);
    }
        
    @Override
    public List<SocialNetwork> verSocial() {
        return socialRepo.findAll(Sort.by("orderdeploy").ascending());
    }

    @Override
    public List<DTOSocialNetwork> verByPersonId(Long id) {
        List<SocialNetwork> listsocial = socialRepo.findByPersonId(id);
        
        // Ordeno la lista obtenida
        listsocial.sort(Comparator.comparing(SocialNetwork::getOrderdeploy));
         
        List listatemp = new ArrayList();
        
        for (SocialNetwork elemento :listsocial) {
            DTOSocialNetwork tempDTO = new DTOSocialNetwork();

            tempDTO.setId(elemento.getId());
            tempDTO.setName(elemento.getName());
            tempDTO.setPathIcon(elemento.getPathIcon());
            tempDTO.setUrl(elemento.getUrl());
            tempDTO.setOrderdeploy(elemento.getOrderdeploy());
            
            listatemp.add(tempDTO);
        }
         
        
        return listatemp;
    }
    
    
    @Override
    public SocialNetwork findByNameAndPersonId(String nombre, Long id) {
        return socialRepo.findByNameAndPersonId(nombre, id);

    }

    @Override
    public boolean existeSoftInPerson(String nombre, Long id, SocialNetwork social) {
        SocialNetwork temp = socialRepo.findByNameAndPersonId(nombre, id);
        if (temp != null) {
            return Objects.equals(temp.getId(), social.getId());
        } else {
            return false;
        }
    }
    
}
