package com.example.ecom.model;


import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Order generated from the website.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WebOrderDTO {

    /** The user of the order. */
    private LocalUser user;
    /** The shipping address of the order. */
    private String address;
    /** The quantities ordered. */
    private List<WebOrderQuantities> quantities;

}

