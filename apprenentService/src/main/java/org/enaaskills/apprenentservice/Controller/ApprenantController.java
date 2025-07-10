package org.enaaskills.apprenentservice.Controller;


import org.enaaskills.apprenentservice.Model.Apprenant;
import org.enaaskills.apprenentservice.Service.ApprenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Apprenant getApprenantById(@PathVariable Long id) {
        return apprenantService.getApprenantById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteApprenant(@PathVariable Long id) {
        apprenantService.deleteApprenant(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apprenant> updateApprenant(@PathVariable Long id, @RequestBody Apprenant apprenant) {
        Apprenant updated = apprenantService.updateApprenant(id, apprenant);
        return ResponseEntity.ok(updated);
    }
}
