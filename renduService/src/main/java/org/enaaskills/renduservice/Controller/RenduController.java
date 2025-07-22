package org.enaaskills.renduservice.Controller;

import org.enaaskills.renduservice.Model.Rendu;
import org.enaaskills.renduservice.Service.RenduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class RenduController {

    @Autowired
    private RenduService renduService;

    @GetMapping
    public List<Rendu> getAllRendus() {
        return renduService.getAllRendus();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rendu> getRenduById(@PathVariable Long id) {
        return renduService.getRenduById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Rendu createRendu(@RequestBody Rendu rendu) {
        return renduService.saveRendu(rendu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rendu> updateRendu(@PathVariable Long id, @RequestBody Rendu rendu) {
        Rendu updated = renduService.updateRendu(id, rendu);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendu(@PathVariable Long id) {
        if (renduService.getRenduById(id).isPresent()) {
            renduService.deleteRendu(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<Map<String, Object>> getRenduDetails(@PathVariable Long id) {
        Optional<Rendu> renduOptional = renduService.getRenduById(id);
        if (renduOptional.isPresent()) {
            Rendu rendu = renduOptional.get();
            Map<String, Object> details = new java.util.HashMap<>();
            details.put("rendu", rendu);

            if (rendu.getApprenantId() != null) {
                Map<String, Object> apprenantDetails = renduService.getApprenantDetails(rendu.getApprenantId());
                if (apprenantDetails != null && !apprenantDetails.containsKey("error")) {
                    details.put("apprenantDetails", apprenantDetails);
                }
            }
            if (rendu.getFormateurId() != null) {
                Map<String, Object> formateurDetails = renduService.getFormateurDetails(rendu.getFormateurId());
                if (formateurDetails != null && !formateurDetails.containsKey("error")) {
                    details.put("formateurDetails", formateurDetails);
                }
            }
            if (rendu.getCompetenceId() != null) {
                Map<String, Object> competenceDetails = renduService.getCompetenceDetails(rendu.getCompetenceId());
                if (competenceDetails != null && !competenceDetails.containsKey("error")) {
                    details.put("competenceDetails", competenceDetails);
                }
            }
            if (rendu.getValidationId() != null) {
                Map<String, Object> validationDetails = renduService.getValidationDetails(rendu.getValidationId());
                if (validationDetails != null && !validationDetails.containsKey("error")) {
                    details.put("validationDetails", validationDetails);
                }
            }
            return ResponseEntity.ok(details);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
