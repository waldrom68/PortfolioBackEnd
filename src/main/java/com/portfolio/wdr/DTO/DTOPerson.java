// Orden de creacion 0, Que es lo que voy a mostrar?

package com.portfolio.wdr.DTO;

import com.portfolio.wdr.model.DisplayData;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOPerson implements Serializable {
    private long id;
    private String name;
    private String lastName;
    private String pathFoto;
    private String location;
    private String profession;
    private String profile;
    private String objetive;
    private Date since;
    private String email;
    private String username;
    private String urlLocation;
    private String pathBgImage;
    private DisplayData displaydata;
}
