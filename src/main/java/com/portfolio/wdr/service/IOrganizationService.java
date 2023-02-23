// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOOrganization;
import com.portfolio.wdr.model.Organization;
import java.util.List;


public interface IOrganizationService {
    
    public Organization crearOrganizacion(Organization orga);
    public void borrarOrganizacion(Long id);
    public Organization buscarOrganizacion(Long id);

    public Organization findByNameAndPersonId(String nombre, Long id);
    public boolean existeInPerson(String nombre, Long id, Organization objeto);
    
    public List<Organization> verOrganizacion();
    public List<DTOOrganization> verByPersonId(Long id);
}
