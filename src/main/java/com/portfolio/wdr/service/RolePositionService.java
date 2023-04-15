// Orden de creacion 4.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTORolePosition;
import com.portfolio.wdr.model.Person;
import com.portfolio.wdr.model.RolePosition;
import com.portfolio.wdr.repository.RolePositionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RolePositionService implements IRolePositionService {
    
    @Autowired
    public RolePositionRepository positionRepo;
    @Autowired public IPersonService persServ;

    @Override
    public RolePosition crearRolePosition(RolePosition position) {
        Long tmp_id = position.getPerson().getId();
        Person pers = persServ.buscarPersona(tmp_id);
        if (pers != null ) {
            position.setPerson(pers);

        } 
        return positionRepo.save(position);
    }

    @Override
    public void borrarRolePosition(Long id) {
        positionRepo.deleteById(id);
    }

    @Override
    public RolePosition buscarRolePosition(Long id) {
        return positionRepo.findById(id).orElse(null);
    }
    
    @Override
    public List<RolePosition> verRolePosition() {
        return positionRepo.findAll(Sort.by("name").ascending());
    }

    @Override
    public List<DTORolePosition> verByPersonId(Long id) {
        List<RolePosition> listrole = positionRepo.findByPersonId(id);
        List listatemp = new ArrayList();
        
        for (RolePosition elemento : listrole) {
            DTORolePosition tempDTO = new DTORolePosition();
       
            tempDTO.setId(elemento.getId());
            tempDTO.setName(elemento.getName());
            
            listatemp.add(tempDTO);
        }
        
        return listatemp;
    }
    
    @Override
    public RolePosition findByNameAndPersonId(String nombre, Long id) {
        return positionRepo.findByNameAndPersonId(nombre, id);

    }

    @Override
    public boolean existeInPerson(String nombre, Long id, RolePosition objeto) {
        RolePosition temp = positionRepo.findByNameAndPersonId(nombre, id);
        if (temp != null) {
            return Objects.equals(temp.getId(), objeto.getId());
        } else {
            return false;
        }
    }
}
