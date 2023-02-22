// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOProject;
import com.portfolio.wdr.model.Project;
import java.util.List;

public interface IProjectService {
    
    public Project crearProject(Project proy);
    public void borrarProject(Long id);
    public Project buscarProject(Long id);
    
    public Project findByNameAndPersonId(String nombre, Long id);
    public boolean existeSoftInPerson(String nombre, Long id, Project objeto);
    
    public List<Project> verProject();
    public List<DTOProject> verByPersonId(Long id);
    
}
