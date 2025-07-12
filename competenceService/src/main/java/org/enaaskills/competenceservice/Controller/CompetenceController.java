package org.enaaskills.competenceservice.Controller;

import org.enaaskills.competenceservice.Model.Competence;
import org.enaaskills.competenceservice.Service.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/competences")
public class CompetenceController {

    @Autowired
    private CompetenceService competenceService;

    @GetMapping
    public List<Competence> getAllCompetences() {
        return competenceService.getAllCompetences();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competence> getCompetenceById(@PathVariable Long id) {
        return competenceService.getCompetenceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Competence createCompetence(@RequestBody Competence competence) {
        return competenceService.saveCompetence(competence);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Competence> updateCompetence(@PathVariable Long id, @RequestBody Competence competence) {
        Competence updated = competenceService.updateCompetence(id, competence);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetence(@PathVariable Long id) {
        if (competenceService.getCompetenceById(id).isPresent()) {
            competenceService.deleteCompetence(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/renduDetails")
    public ResponseEntity<Map<String, Object>> getRenduDetails(@PathVariable Long id) {
        Optional<Competence> competenceOptional = competenceService.getCompetenceById(id);
        if (competenceOptional.isPresent()) {
            // Assuming a Rendu can be associated with a Competence, and we need to fetch its details.
            // This part needs to be carefully designed based on how Rendu and Competence are linked.
            // For now, let's assume Competence has a direct link to a Rendu ID for demonstration.
            // If not, you'd need to query RenduService for Rendus associated with this Competence.
            // As per the provided schema, Rendu is linked to Competence (many-to-many via +contient).
            // This example assumes a direct Rendu ID for simplicity. A more complex solution would involve
            // querying the RenduService for all Rendus that contain this Competence.
            // For now, let's just return a placeholder or an error if no direct Rendu ID is available.
            return ResponseEntity.notFound().build(); // Placeholder for now
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
