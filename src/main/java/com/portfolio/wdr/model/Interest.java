// Orden de creacion 1.-

package com.portfolio.wdr.model;

// for reference off general usage view https://jakarta.ee/specifications/persistence/3.0/jakarta-persistence-spec-3.0.html

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

// p.ej.: @Column(updatable = false), @ManyToOne, @JoinTable
//     @MapKeyJoinColumn(name="MOVIE", referencedColumnName="ID")
//     Map<Movie, Integer> videoInventory;
//
//@Entity
//public class CreditCard {
//    @Id
//    long Number;
//
//    @OneToMany // unidirectional
//    @OrderColumn
//    List<CardTransaction> transactionHistory;
//
//    // ...
//}

@Getter @Setter
@Entity
public class Interest {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false, length=75)
    private String name;
    private int orderdeploy = 0;

    // El Many apunta a esta Entity y el One al modelo de la relacion
    // Si deja de existir este registro, debe mantener a quien lo contenía.
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="person")
    Person person;

    public Interest() {
    }

    public Interest(String name, Person person) {
        this.name = name;
        this.person = person;
    }

    public Interest(Long id, String name, Person person) {
        this.id = id;
        this.name = name;
        this.person = person;
    }
    

    @Override
    public String toString() {
        return "Interest{" + "id=" + id + ", name=" + name + ", orderdeploy=" + orderdeploy + ", person=" + person + '}';
    }
    
    
    
    
}
