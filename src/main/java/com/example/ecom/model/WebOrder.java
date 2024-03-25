package com.example.ecom.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Order generated from the website.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WebOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /** The user of the order. */
    @ManyToOne(optional = false)
    private LocalUser user;
    /** The shipping address of the order. */
    @ManyToOne(optional = false)
    private Address address;
    /** The quantities ordered. */
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<WebOrderQuantities> quantities = new ArrayList<>();

}
