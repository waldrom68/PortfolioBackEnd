// Orden de creacion 0, Que es lo que voy a mostrar?

package com.portfolio.wdr.DTO;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOProject implements Serializable {
    
    private long id;
    private String name;
    private String resume;
    private String url;
    private Date since;
    private int orderdeploy = 0;

    public DTOProject() {
    }
    
    
}

