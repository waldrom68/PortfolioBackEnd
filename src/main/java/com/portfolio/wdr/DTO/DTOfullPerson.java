// Orden de creacion 0, Que es lo que voy a mostrar?

package com.portfolio.wdr.DTO;

import com.portfolio.wdr.model.DisplayData;
import com.portfolio.wdr.model.LaboralCareer;


import com.portfolio.wdr.model.Phone;
import com.portfolio.wdr.model.SocialNetwork;
import com.portfolio.wdr.model.Studie;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOfullPerson implements Serializable {
    private long id;
    private String name;
    private String lastName;
    private String pathFoto;
    private String location;
    private String profession;
    private String profile;
    private String objetive;
    private String since;
    private String email;
    private String username;
    private DisplayData displaydata;
    private List <DTOHardskill> hardskill;
    private List <DTOSoftskill> softskill;
    private List <DTOInterest> interest;
    private List <DTOLaboralCareer> laboralCareer;
    private List <DTOStudie> studie;
    private List <DTOPhone> phone;
    private List <DTOSocialNetwork> socialnetwork;
    private List <DTOProject> project;

    public DTOfullPerson() {
    }
    
    
}




