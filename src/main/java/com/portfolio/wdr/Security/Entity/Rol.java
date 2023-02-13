
package com.portfolio.wdr.Security.Entity;

import com.portfolio.wdr.Security.Enums.RolNombre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Rol {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private int id;
    
    @Column(nullable=false)
    @Enumerated (EnumType.STRING)
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
    
}
