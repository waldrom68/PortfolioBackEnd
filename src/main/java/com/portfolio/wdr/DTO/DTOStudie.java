// Orden de creacion 0, Que es lo que voy a mostrar?

package com.portfolio.wdr.DTO;

import com.portfolio.wdr.model.Degree;
import com.portfolio.wdr.model.Organization;
import java.io.Serializable;
import java.util.Date;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOStudie  implements Serializable {
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private int orderdeploy ;
    private boolean status;
    
    private Organization organization;
    private Degree degree;


    public DTOStudie() {
    }
    
}

