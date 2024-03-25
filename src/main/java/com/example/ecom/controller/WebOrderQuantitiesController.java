package com.example.ecom.controller;

import com.example.ecom.model.WebOrderQuantities;
import com.example.ecom.service.WebOrderQuantitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weborderquantities")
public class WebOrderQuantitiesController {

    @Autowired
    private WebOrderQuantitiesService webOrderQuantitiesService;

    @PostMapping("/saveweborderquantities")
    public WebOrderQuantities saveWebOrderQuantities(@RequestBody WebOrderQuantities webOrderQuantities) {
        return webOrderQuantitiesService.saveWebOrderQuantities(webOrderQuantities);
    }

    @GetMapping("/getallweborderquantities")
    public List<WebOrderQuantities> getAllWebOrderQuantities() {
        return webOrderQuantitiesService.getAllWebOrderQuantities();
    }

    @GetMapping("/getweborderquantities/{id}")
    public ResponseEntity<WebOrderQuantities> getWebOrderQuantitiesById(@PathVariable Long id) {
        Optional<WebOrderQuantities> webOrderQuantities = webOrderQuantitiesService.getWebOrderQuantitiesById(id);
        return webOrderQuantities.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/updateweborderquantities/{id}")
    public ResponseEntity<WebOrderQuantities> updateWebOrderQuantities(@PathVariable Long id, @RequestBody WebOrderQuantities updatedWebOrderQuantities) {
        WebOrderQuantities result = webOrderQuantitiesService.updateWebOrderQuantities(id, updatedWebOrderQuantities);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteweborderquantities/{id}")
    public ResponseEntity<Void> deleteWebOrderQuantities(@PathVariable Long id) {
        webOrderQuantitiesService.deleteWebOrderQuantities(id);
        return ResponseEntity.noContent().build();
    }
}
