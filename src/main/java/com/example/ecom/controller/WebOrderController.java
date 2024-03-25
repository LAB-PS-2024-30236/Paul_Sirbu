package com.example.ecom.controller;

import com.example.ecom.model.WebOrder;
import com.example.ecom.service.WebOrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public WebOrder saveWebOrder(@RequestBody WebOrder webOrder) {
        return webOrderService.saveWebOrder(webOrder);
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
}
