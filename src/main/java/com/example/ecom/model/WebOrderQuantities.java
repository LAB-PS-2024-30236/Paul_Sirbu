package com.example.ecom.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The quantity ordered of a product.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WebOrderQuantities {

    /** The unqiue id of the order quantity. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The product being ordered. */
    @ManyToOne(optional = false)
    private Product product;

    /** The quantity being ordered. */
    private Integer quantity;


}