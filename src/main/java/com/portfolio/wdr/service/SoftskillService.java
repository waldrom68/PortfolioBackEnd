// Orden de creacion 4.-
package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOSoftskill;
import com.portfolio.wdr.model.Person;
import com.portfolio.wdr.model.Softskill;
import com.portfolio.wdr.repository.SoftskillRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SoftskillService implements ISoftskillService {

    // Para la conecion con el JPA : PersonRepository hara de intermediario entre
    // la DB y nuestros metodos, para ello deberemos inyectar nuestra dependencia
    @Autowired
    public SoftskillRepository softRepo;
    @Autowired
    public IPersonService persServ;

    @Override
    public Softskill crearSoft(Softskill soft) {
        // recupero del JSON el id del modelo person como un Long
        // como tengo creado un constructor Person(Long id), obtengo un 
        // objeto persona con sólo el id, así puedo buscarlo en el repository
        // y si existe dicho id, recupero sus valores completos para incluirlo
        // en el objeto Softskill dentro de su relacion.
        Long tmp_id = soft.getPerson().getId();
        Person pers = persServ.buscarPersona(tmp_id);

        if (pers != null) {
            soft.setPerson(pers);

        }

        return softRepo.save(soft);

    }

    @Override
    public void borrarSoft(Long id) {
        softRepo.deleteById(id);
    }

    @Override
    public Softskill buscarSoft(Long id) {
        return softRepo.findById(id).orElse(null);
    }

    @Override
    public List<Softskill> verSoft() {

        return softRepo.findAll(Sort.by("orderdeploy").ascending().and(Sort.by("name")));
    }

    @Override
    public List<DTOSoftskill> verByPersonId(Long id) {
//        Person pers = persServ.buscarPersona(id);
        List<Softskill> listsoft = softRepo.findByPersonId(id);

        // Ordeno la lista obtenida
        listsoft.sort(Comparator.comparing(softskill -> softskill.getOrderdeploy()));

        List listatemp = new ArrayList();

        for (Softskill elemento : listsoft) {
            DTOSoftskill tempDTO = new DTOSoftskill();

            tempDTO.setId(elemento.getId());
            tempDTO.setName(elemento.getName());
            tempDTO.setAssessment(elemento.getAssessment());
            tempDTO.setOrderdeploy(elemento.getOrderdeploy());

            listatemp.add(tempDTO);
        }

        return listatemp;

    }

    @Override
    public Softskill findByNameAndPersonId(String nombre, Long id) {
          return softRepo.findByNameAndPersonId(nombre, id);

    }

    @Override
    public boolean existeSoftInPerson(String nombre, Long id, Softskill soft) {
        Softskill temp = softRepo.findByNameAndPersonId(nombre, id);
        if (temp != null){
            return Objects.equals(temp.getId(), soft.getId());
        } else {
        return false;
        }
    }
}

// para una prueba
//    @Override
//    public List<Softskill> verByAssesment(int puntaje) {
//        return softRepo.findByAssessment(puntaje);
//    }
