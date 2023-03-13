// Orden de creacion 4.-
package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTODegree;
import com.portfolio.wdr.DTO.DTOfullPerson;
import com.portfolio.wdr.DTO.DTOHardskill;
import com.portfolio.wdr.DTO.DTOInterest;
import com.portfolio.wdr.DTO.DTOLaboralCareer;
import com.portfolio.wdr.DTO.DTOPhone;
import com.portfolio.wdr.DTO.DTOProject;
import com.portfolio.wdr.DTO.DTOSocialNetwork;
import com.portfolio.wdr.DTO.DTOSoftskill;
import com.portfolio.wdr.DTO.DTOStudie;
//import com.portfolio.wdr.DTO.DTOFullPerson;
import com.portfolio.wdr.DTO.DTOOrganization;
import com.portfolio.wdr.DTO.DTORolePosition;
import com.portfolio.wdr.model.Person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class fullPersonService implements IFullPersonService {

    // Para la conexion con el JPA : PersonRepository hara de intermediario entre
    // la DB y nuestros metodos, para ello deberemos inyectar nuestra dependencia
    @Autowired public PersonService persoServ;
    @Autowired private IHardskillService hardServ;
    @Autowired private ISoftskillService softServ;
    @Autowired private IInterestService intServ;
    @Autowired private ILaboralCareerService labServ;
    @Autowired private IOrganizationService orgaServ;
    @Autowired private IDegreeService degreeServ;
    @Autowired private IRolePositionService positionServ;
   
    @Autowired private IStudieService studServ;
    @Autowired private IPhoneService phonServ;
    @Autowired private ISocialnetworkService socialServ;
    @Autowired private IProjectService projServ;

    @Override
    public DTOfullPerson verPersona(Long id) {

        Person tempper = persoServ.buscarPersona(id);
        DTOfullPerson tempDTO = new DTOfullPerson();

        List<DTOHardskill> temphard = hardServ.verByPersonId(id);
        List<DTOSoftskill> tempsoft = softServ.verByPersonId(id);
        List<DTOInterest> tempint = intServ.verByPersonId(id);
        
        List<DTOOrganization> temporga = orgaServ.verByPersonId(id);
        List<DTODegree> tempdegree = degreeServ.verByPersonId(id);
        List<DTORolePosition> tempposition = positionServ.verByPersonId(id);
        
        
        List<DTOLaboralCareer> templab = labServ.verByPersonId(id);
        List<DTOStudie> tempstu = studServ.verByPersonId(id);
        List<DTOPhone> tempphon = phonServ.verByPersonId(id);
        List<DTOSocialNetwork> tempsoc = socialServ.verByPersonId(id);
        List<DTOProject> tempproj = projServ.verByPersonId(id);

        tempDTO.setId(tempper.getId());
        tempDTO.setName(tempper.getName());
        tempDTO.setLastName(tempper.getLastName());
        tempDTO.setPathFoto(tempper.getPathFoto());
        tempDTO.setLocation(tempper.getLocation());
        tempDTO.setProfession(tempper.getProfession());
        tempDTO.setProfile(tempper.getProfile());
        tempDTO.setObjetive(tempper.getObjetive());
//        String temp = tempper.getSince().toString();
//        tempDTO.setSince(temp.substring(0, 10));
        tempDTO.setSince(tempper.getSince());
        tempDTO.setEmail(tempper.getEmail());
        tempDTO.setUrlLocation(tempper.getUrlLocation());
                
//        tempDTO.setUsername(tempper.getUsername());
        tempDTO.setDisplaydata(tempper.getDisplaydata());
        tempDTO.setHardskill(temphard);
        tempDTO.setSoftskill(tempsoft);
        tempDTO.setInterest(tempint);
        
        tempDTO.setDegree(tempdegree);
        tempDTO.setOrganization(temporga);
        tempDTO.setRoleposition(tempposition);
        
        
        tempDTO.setLaboralCareer(templab);
        tempDTO.setStudie(tempstu);
        tempDTO.setPhone(tempphon);
        tempDTO.setSocialnetwork(tempsoc);
        tempDTO.setProject(tempproj);

        return tempDTO;
    }

//        throw new UnsupportedOperationException("Not supported yet.");
    @Override
    public boolean existsPersonById(Long id) {
        return persoServ.existsPersonById(id);
    }

}
