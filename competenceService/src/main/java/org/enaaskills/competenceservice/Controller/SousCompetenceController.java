package org.enaaskills.competenceservice.Controller;

import org.enaaskills.competenceservice.Model.SousCompetence;
import org.enaaskills.competenceservice.Service.SousCompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/souscompetences")
public class SousCompetenceController {

    @Autowired
    private SousCompetenceService sousCompetenceService;

    @GetMapping
    public List<SousCompetence> getAllSousCompetences() {
        return sousCompetenceService.getAllSousCompetences();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SousCompetence> getSousCompetenceById(@PathVariable Long id) {
        return sousCompetenceService.getSousCompetenceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SousCompetence createSousCompetence(@RequestBody SousCompetence sousCompetence) {
        return sousCompetenceService.saveSousCompetence(sousCompetence);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SousCompetence> updateSousCompetence(@PathVariable Long id, @RequestBody SousCompetence sousCompetence) {
        return sousCompetenceService.getSousCompetenceById(id)
                .map(existingSousCompetence -> {
                    existingSousCompetence.setTitre(sousCompetence.getTitre());
                    existingSousCompetence.setValidation(sousCompetence.isValidated());
                    return ResponseEntity.ok(sousCompetenceService.saveSousCompetence(existingSousCompetence));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSousCompetence(@PathVariable Long id) {
        if (sousCompetenceService.getSousCompetenceById(id).isPresent()) {
            sousCompetenceService.deleteSousCompetence(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
