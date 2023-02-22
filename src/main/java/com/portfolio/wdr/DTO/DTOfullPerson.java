// Orden de creacion 0, Que es lo que voy a mostrar?

package com.portfolio.wdr.DTO;

import com.portfolio.wdr.model.DisplayData;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOFullPerson implements Serializable {
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
    private String urlLocation;
//    private String username;
    private DisplayData displaydata;
    private List <DTOHardskill> hardskill;
    private List <DTOSoftskill> softskill;
    private List <DTOInterest> interest;
    private List <DTOLaboralCareer> laboralCareer;
    private List <DTOStudie> studie;
    private List <DTOPhone> phone;
    private List <DTOSocialNetwork> socialnetwork;
    private List <DTOProject> project;

    public DTOFullPerson() {
    }
    
    
}




