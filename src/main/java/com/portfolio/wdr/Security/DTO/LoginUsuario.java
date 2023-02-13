
package com.portfolio.wdr.Security.DTO;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;



@Getter @Setter
public class LoginUsuario {
    @Column(nullable=false)
    private String nombreUsuario;
    @Column(nullable=false)
    private String password;
    
    
}
