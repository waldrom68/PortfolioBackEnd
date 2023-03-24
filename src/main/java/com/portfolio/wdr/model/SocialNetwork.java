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
public class SocialNetwork {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable=false, length=45)
    private String name;
    private int orderdeploy = 0 ;
    @Column(length=45)
    private String iconname;
    
    
    @Column(nullable=false, length=250)
    private String url;
    
    
    // El Many apunta a esta Entity y el One al modelo de la relacion
    // Si deja de existir este registro, debe mantener a quien lo contenía.
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="person")
    Person person;

    public SocialNetwork() {
    }

//    public SocialNetwork(String name, String iconname, String url, Person person) {
//        this.name = name;
//        this.iconname = iconname;
//        this.url = url;
//
//        this.person = person;
//    }

    public SocialNetwork(long id, String name, String iconname, String url, Person person) {
        this.id = id;
        this.name = name;
        this.iconname = iconname;
        this.url = url;
        this.person = person;
    }


    
@Override
      public String toString() {
        return "SocialNetwork{" + "id=" + id + ", name=" + name +
                ", orderdeploy=" + orderdeploy + 
                ", iconname=" + iconname +
                ", url=" + url +
                ", person=" + person + '}';
    }
    
    
    
}
