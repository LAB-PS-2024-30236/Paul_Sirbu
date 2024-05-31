package com.example.ecom.repository;

import com.example.ecom.model.WebOrderQuantities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebOrderQuantitiesRepository extends JpaRepository<WebOrderQuantities, Long> {
}
