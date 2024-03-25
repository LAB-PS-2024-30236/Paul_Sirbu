package com.example.ecom.service;

import com.example.ecom.model.WebOrderQuantities;
import com.example.ecom.repository.WebOrderQuantitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebOrderQuantitiesService {

    @Autowired
    private WebOrderQuantitiesRepository webOrderQuantitiesRepository;

    public List<WebOrderQuantities> getAllWebOrderQuantities() {
        return webOrderQuantitiesRepository.findAll();
    }

    public Optional<WebOrderQuantities> getWebOrderQuantitiesById(Long id) {
        return webOrderQuantitiesRepository.findById(id);
    }

    public WebOrderQuantities saveWebOrderQuantities(WebOrderQuantities webOrderQuantities) {
        return webOrderQuantitiesRepository.save(webOrderQuantities);
    }

    public void deleteWebOrderQuantities(Long id) {
        webOrderQuantitiesRepository.deleteById(id);
    }

    public WebOrderQuantities updateWebOrderQuantities(Long id, WebOrderQuantities updatedWebOrderQuantities) {
        Optional<WebOrderQuantities> existingWebOrderQuantitiesOptional = webOrderQuantitiesRepository.findById(id);
        if (existingWebOrderQuantitiesOptional.isPresent()) {
            WebOrderQuantities existingWebOrderQuantities = existingWebOrderQuantitiesOptional.get();
            existingWebOrderQuantities.setProduct(updatedWebOrderQuantities.getProduct());
            existingWebOrderQuantities.setQuantity(updatedWebOrderQuantities.getQuantity());
            existingWebOrderQuantities.setOrder(updatedWebOrderQuantities.getOrder());
            // Update other fields as needed
            return webOrderQuantitiesRepository.save(existingWebOrderQuantities);
        } else {
            // Handle the case where the web order quantities with the given ID is not found
            // You may throw an exception or return null, depending on your application's requirements
            return null;
        }
    }
}
