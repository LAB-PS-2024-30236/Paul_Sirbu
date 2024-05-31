package com.example.ecom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Inventory of a product that available for purchase.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Inventory {

    /** Unique id for the inventory. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /** The product this inventory is of. */
    @OneToOne(optional = false, orphanRemoval = true)
    private Product product;
    /** The quantity in stock. */
    private Integer quantity;

}