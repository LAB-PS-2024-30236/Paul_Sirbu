package com.example.ecom.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Order generated from the website.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
public class WebOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /** The user of the order. */
    @ManyToOne(optional = false)
    private LocalUser user;
    /** The shipping address of the order. */
    private String address;
    /** The quantities ordered. */
    @Transient
    private List<WebOrderQuantities> quantities;

    public WebOrder(LocalUser user, String address, List<WebOrderQuantities> quantities){
        this.user=user;
        this.address=address;
        this.quantities=quantities;
        this.id=new Random().nextLong();
    }

}
