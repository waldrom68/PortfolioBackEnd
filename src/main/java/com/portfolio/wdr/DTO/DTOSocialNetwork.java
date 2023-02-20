// Orden de creacion 0, Que es lo que voy a mostrar?

package com.portfolio.wdr.DTO;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOSocialNetwork  implements Serializable {
    private long id;
    private String name;
    private String pathIcon;
    private String url;
    private int orderdeploy;

    public DTOSocialNetwork() {
    }
    
}