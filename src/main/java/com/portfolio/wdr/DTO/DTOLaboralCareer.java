// Orden de creacion 0, Que es lo que voy a mostrar?

package com.portfolio.wdr.DTO;

import com.portfolio.wdr.model.Organization;
import com.portfolio.wdr.model.RolePosition;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOLaboralCareer   implements Serializable {
    private Long id;
//    private String position;
    private String resume;
    private String startDate;
    private String endDate;
    private int orderdeploy ;
    private boolean status;
    
    private Organization organization;
    private RolePosition roleposition;

    public DTOLaboralCareer() {
    }
    
    
}

