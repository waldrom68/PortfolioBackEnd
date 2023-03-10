// Orden de creacion 1.-

package com.portfolio.wdr.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


// for reference off general usage view https://jakarta.ee/specifications/persistence/3.0/jakarta-persistence-spec-3.0.html
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
@ToString
@Getter @Setter
@Entity
public class Studie {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false, length=100)
    private String name;
    
    @Column(nullable=false)
    private Date startDate;
    
    private Date endDate;
    
    @Column(nullable=false)
    private int orderdeploy = 0;
    
    @Column(nullable=false)
    private boolean status = true;

    
    // El Many apunta a esta Entity y el One al modelo de la relacion
    // Si deja de existir este registro, debe mantener a quien lo contenía.
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="person")
    Person person;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="organization")
    Organization organization;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="degree")
    Degree degree;
    
    public Studie() {
    }

    public Studie(String name, Date startDate, Date endDate, Person person, Organization organization, Degree degree) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.person = person;
        this.organization = organization;
        this.degree = degree;
    }


 
}
