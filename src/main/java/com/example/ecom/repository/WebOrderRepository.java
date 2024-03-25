package com.example.ecom.repository;

import com.example.ecom.model.WebOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebOrderRepository extends JpaRepository<WebOrder, Long> {
}
