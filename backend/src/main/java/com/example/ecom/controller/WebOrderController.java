package com.example.ecom.controller;

import com.example.ecom.model.WebOrder;
import com.example.ecom.model.WebOrderDTO;
import com.example.ecom.service.WebOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weborders")
public class WebOrderController {

    @Autowired
    private WebOrderService webOrderService;

    @PostMapping("/saveweborder")
    public ResponseEntity<WebOrder> saveWebOrder(@RequestBody WebOrderDTO webOrder) {
        if (webOrder.getAddress() == null || webOrder.getAddress().isEmpty()) {
            // Handle missing address
            return ResponseEntity.badRequest().build();
        }

        WebOrder savedWebOrder = webOrderService.saveWebOrder(new WebOrder(webOrder.getUser(), webOrder.getAddress(), webOrder.getQuantities()));
        if (savedWebOrder != null) {
            return ResponseEntity.ok(savedWebOrder);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/getallweborders")
    public List<WebOrder> getAllWebOrders() {
        return webOrderService.getAllWebOrders();
    }

    @GetMapping("/getweborder/{id}")
    public ResponseEntity<WebOrder> getWebOrderById(@PathVariable Long id) {
        Optional<WebOrder> webOrder = webOrderService.getWebOrderById(id);
        return webOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/updateweborder/{id}")
    public ResponseEntity<WebOrder> updateWebOrder(@PathVariable Long id, @RequestBody WebOrder updatedWebOrder) {
        WebOrder result = webOrderService.updateWebOrder(id, updatedWebOrder);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteweborder/{id}")
    public ResponseEntity<Void> deleteWebOrder(@PathVariable Long id) {
        webOrderService.deleteWebOrder(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getwebordersbyuser/{userId}")
    public ResponseEntity<List<WebOrder>> getWebOrdersByUser(@PathVariable Long userId) {
        List<WebOrder> webOrders = webOrderService.getWebOrdersByUser(userId);
        return ResponseEntity.ok(webOrders);
    }
}
