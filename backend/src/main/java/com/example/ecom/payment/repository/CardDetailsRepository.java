package com.example.ecom.payment.repository;


import com.example.ecom.payment.model.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDetailsRepository extends JpaRepository<CardDetails, Long> {
}
