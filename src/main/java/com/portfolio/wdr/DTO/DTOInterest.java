// Orden de creacion 0, Que es lo que voy a mostrar?

package com.portfolio.SpringBoot.DTO;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOInterest implements Serializable {
    
        private Long id;
        private String name;
        private int orderdeploy;

    public DTOInterest() {
    }

}
