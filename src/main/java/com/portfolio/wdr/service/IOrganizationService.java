// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.model.Organization;
import java.util.List;


public interface IOrganizationService {
    
    public Organization crearOrganizacion(Organization orga);
    public void borrarOrganizacion(Long id);
    public Organization buscarOrganizacion(Long id);
    
    public List<Organization> verOrganizacion();
    public List<Organization> verByPersonId(Long id);
}
