// Orden de creacion 4.-
package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTODegree;
import com.portfolio.wdr.model.Degree;
import com.portfolio.wdr.model.Person;
import com.portfolio.wdr.repository.DegreeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DegreeService implements IDegreeService {

    // Para la conecion con el JPA : PersonRepository hara de intermediario entre
    // la DB y nuestros metodos, para ello deberemos inyectar nuestra dependencia
    @Autowired
    public DegreeRepository degreeRepo;
    @Autowired
    public IPersonService persServ;

    @Override
    public Degree crearDegree(Degree deg) {
        Long tmp_id = deg.getPerson().getId();
        Person pers = persServ.buscarPersona(tmp_id);

        if (pers != null) {
            deg.setPerson(pers);

        }
        return degreeRepo.save(deg);
    }

    @Override
    public void borrarDegree(Long id) {
        degreeRepo.deleteById(id);
    }

    @Override
    public Degree buscarDegree(Long id) {
        return degreeRepo.findById(id).orElse(null);
    }

    @Override
    public List<Degree> verByPersonId(Long id) {
        List<Degree> liststudie = degreeRepo.findByPersonId(id);
        List listatemp = new ArrayList();

        for (Degree elemento : liststudie) {
            DTODegree tempDTO = new DTODegree();

            tempDTO.setId(elemento.getId());
            tempDTO.setName(elemento.getName());

            listatemp.add(tempDTO);

        }

        return listatemp;
    }

    @Override
    public Degree findByNameAndPersonId(String nombre, Long id) {
        return degreeRepo.findByNameAndPersonId(nombre, id);

    }

    @Override
    public boolean existeInPerson(String nombre, Long id, Degree objeto) {
        Degree temp = degreeRepo.findByNameAndPersonId(nombre, id);
        if (temp != null) {
            return Objects.equals(temp.getId(), objeto.getId());
        } else {
            return false;
        }
    }
}
