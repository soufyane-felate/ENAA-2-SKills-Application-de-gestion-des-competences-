package org.enaaskills.apprenentservice.Controller;


import org.enaaskills.apprenentservice.Model.Apprenant;
import org.enaaskills.apprenentservice.Service.ApprenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apprenants")
public class ApprenantController {

    @Autowired
    private ApprenantService apprenantService;

    @PostMapping
    public Apprenant createApprenant(@RequestBody Apprenant apprenant) {
        return apprenantService.saveApprenant(apprenant);
    }

    @GetMapping
    public List<Apprenant> getAllApprenants() {
        return apprenantService.getAllApprenants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apprenant> getApprenantById(@PathVariable Long id) {
        Apprenant apprenant = apprenantService.getApprenantById(id);
        if (apprenant != null) {
            return ResponseEntity.ok(apprenant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApprenant(@PathVariable Long id) {
        apprenantService.deleteApprenant(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apprenant> updateApprenant(@PathVariable Long id, @RequestBody Apprenant apprenant) {
        Apprenant updated = apprenantService.updateApprenant(id, apprenant);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<Map<String, Object>> getApprenantDetails(@PathVariable Long id) {
        Apprenant apprenant = apprenantService.getApprenantById(id);
        if (apprenant != null && apprenant.getRenduId() != null) {
            Map<String, Object> renduDetails = apprenantService.getRenduDetails(apprenant.getRenduId());
            if (renduDetails != null) {
                return ResponseEntity.ok(renduDetails);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
