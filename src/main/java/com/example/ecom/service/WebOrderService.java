package com.example.ecom.service;

import com.example.ecom.model.WebOrder;
import com.example.ecom.repository.WebOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebOrderService {

    @Autowired
    private WebOrderRepository webOrderRepository;

    public List<WebOrder> getAllWebOrders() {
        return webOrderRepository.findAll();
    }

    public Optional<WebOrder> getWebOrderById(Long id) {
        return webOrderRepository.findById(id);
    }

    public WebOrder saveWebOrder(WebOrder webOrder) {
        return webOrderRepository.save(webOrder);
    }

    public void deleteWebOrder(Long id) {
        webOrderRepository.deleteById(id);
    }

    public WebOrder updateWebOrder(Long id, WebOrder updatedWebOrder) {
        Optional<WebOrder> existingWebOrderOptional = webOrderRepository.findById(id);
        if (existingWebOrderOptional.isPresent()) {
            WebOrder existingWebOrder = existingWebOrderOptional.get();
            existingWebOrder.setUser(updatedWebOrder.getUser());
            existingWebOrder.setAddress(updatedWebOrder.getAddress());
            existingWebOrder.setQuantities(updatedWebOrder.getQuantities());
            // Update other fields as needed
            return webOrderRepository.save(existingWebOrder);
        } else {
            // Handle the case where the web order with the given ID is not found
            // You may throw an exception or return null, depending on your application's requirements
            return null;
        }
    }
}
