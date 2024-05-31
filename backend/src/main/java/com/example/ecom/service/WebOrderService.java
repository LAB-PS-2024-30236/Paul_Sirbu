package com.example.ecom.service;

import com.example.ecom.model.LocalUser;
import com.example.ecom.model.WebOrder;
import com.example.ecom.repository.LocalUserRepository;
import com.example.ecom.repository.WebOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebOrderService {

    @Autowired
    private WebOrderRepository webOrderRepository;

    @Autowired
    private LocalUserRepository localUserRepository;

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

    public List<WebOrder> getWebOrdersByUser(Long userId) {
        // First, you might want to check if the user exists
        Optional<LocalUser> userOptional = localUserRepository.findById(userId);
        if (userOptional.isPresent()) {
            // User exists, proceed to retrieve web orders
            return webOrderRepository.findByUserId(userId);
        } else {
            // User does not exist, return an empty list or handle as needed
            return List.of(); // Alternatively, you can throw an exception
        }
    }
}
